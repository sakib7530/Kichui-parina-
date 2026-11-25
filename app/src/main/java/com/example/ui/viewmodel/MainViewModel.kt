package com.example.ui.viewmodel

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.local.entity.LessonCardEntity
import com.example.data.local.entity.LessonEntity
import com.example.data.local.entity.PdfBookmarkEntity
import com.example.data.local.entity.QuizResultEntity
import com.example.data.preferences.UserPreferencesRepository
import com.example.data.preferences.UserSettings
import com.example.data.repository.LearningRepository
import com.example.data.repository.LessonWithProgress
import com.example.notifications.DailyReminderScheduler
import com.example.ui.theme.AppThemeMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class LessonFilter {
    ALL, BOOKMARKED, COMPLETED, REMAINING
}

data class AppDashboardState(
    val isReady: Boolean = false,
    val lessons: List<LessonWithProgress> = emptyList(),
    val filteredLessons: List<LessonWithProgress> = emptyList(),
    val searchQuery: String = "",
    val activeFilter: LessonFilter = LessonFilter.ALL,
    val completedCount: Int = 0,
    val totalCount: Int = 0,
    val progressPercent: Int = 0,
    val continueLesson: LessonWithProgress? = null,
    val bookmarkedCount: Int = 0,
    val quizResults: List<QuizResultEntity> = emptyList(),
    val pdfBookmarks: List<PdfBookmarkEntity> = emptyList(),
    val settings: UserSettings = UserSettings(),
    val vocabCards: List<LessonCardEntity> = emptyList(),
    val verbCards: List<LessonCardEntity> = emptyList()
)

class MainViewModel(
    private val repository: LearningRepository,
    private val prefsRepo: UserPreferencesRepository
) : ViewModel() {

    private val _isSeeded = MutableStateFlow(false)
    private val _searchQuery = MutableStateFlow("")
    private val _activeFilter = MutableStateFlow(LessonFilter.ALL)

    init {
        viewModelScope.launch {
            repository.ensureSeeded()
            _isSeeded.value = true
        }
    }

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    private val searchedIdsFlow = _searchQuery.flatMapLatest { q ->
        repository.searchLessons(q)
    }

    val uiState: StateFlow<AppDashboardState> = combine(
        _isSeeded,
        repository.observeLessonsWithProgress(),
        searchedIdsFlow,
        _searchQuery,
        _activeFilter,
        repository.quizResults,
        repository.pdfBookmarks,
        prefsRepo.settings,
        repository.getCardsByType("VOCAB"),
        repository.getCardsByType("VERB")
    ) { values ->
        @Suppress("UNCHECKED_CAST")
        val seeded = values[0] as Boolean
        @Suppress("UNCHECKED_CAST")
        val allLessons = values[1] as List<LessonWithProgress>
        @Suppress("UNCHECKED_CAST")
        val matchedLessons = values[2] as List<LessonEntity>
        val query = values[3] as String
        val filter = values[4] as LessonFilter
        @Suppress("UNCHECKED_CAST")
        val quizResults = values[5] as List<QuizResultEntity>
        @Suppress("UNCHECKED_CAST")
        val pdfBookmarks = values[6] as List<PdfBookmarkEntity>
        val settings = values[7] as UserSettings
        @Suppress("UNCHECKED_CAST")
        val vocabCards = values[8] as List<LessonCardEntity>
        @Suppress("UNCHECKED_CAST")
        val verbCards = values[9] as List<LessonCardEntity>

        val matchedIdSet = matchedLessons.map { it.id }.toSet()
        val filtered = allLessons.filter { item ->
            val matchesSearch = item.lesson.id in matchedIdSet
            val matchesFilter = when (filter) {
                LessonFilter.ALL -> true
                LessonFilter.BOOKMARKED -> item.isBookmarked
                LessonFilter.COMPLETED -> item.isCompleted
                LessonFilter.REMAINING -> !item.isCompleted
            }
            matchesSearch && matchesFilter
        }

        val completedCount = allLessons.count { it.isCompleted }
        val totalCount = allLessons.size
        val percent = if (totalCount == 0) 0 else ((completedCount * 100f) / totalCount).toInt()
        val continueLesson = allLessons.firstOrNull { it.lesson.id == settings.lastOpenedLessonId }
            ?: allLessons.firstOrNull { !it.isCompleted }
            ?: allLessons.firstOrNull()

        AppDashboardState(
            isReady = seeded,
            lessons = allLessons,
            filteredLessons = filtered,
            searchQuery = query,
            activeFilter = filter,
            completedCount = completedCount,
            totalCount = totalCount,
            progressPercent = percent,
            continueLesson = continueLesson,
            bookmarkedCount = allLessons.count { it.isBookmarked },
            quizResults = quizResults,
            pdfBookmarks = pdfBookmarks,
            settings = settings,
            vocabCards = vocabCards,
            verbCards = verbCards
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AppDashboardState()
    )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun updateFilter(filter: LessonFilter) {
        _activeFilter.value = filter
    }

    fun toggleLessonBookmark(lessonId: String) {
        viewModelScope.launch { repository.toggleBookmark(lessonId) }
    }

    fun toggleLessonCompleted(lessonId: String) {
        viewModelScope.launch { repository.toggleCompleted(lessonId) }
    }

    fun setThemeMode(mode: AppThemeMode) {
        viewModelScope.launch { prefsRepo.setThemeMode(mode) }
    }

    fun updateDailyReminder(context: Context, enabled: Boolean, hour: Int, minute: Int) {
        viewModelScope.launch {
            prefsRepo.setDailyReminder(enabled, hour, minute)
            if (enabled) {
                DailyReminderScheduler.scheduleDaily(context, hour, minute)
            } else {
                DailyReminderScheduler.cancel(context)
            }
        }
    }

    fun sendTestNotification(context: Context) {
        DailyReminderScheduler.triggerImmediateTestNotification(context)
    }

    fun importPdfFromUri(context: Context, uri: Uri) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                runCatching {
                    val resolver = context.contentResolver
                    val name = resolver.query(uri, null, null, null, null)?.use { cursor ->
                        val idx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        if (idx != -1 && cursor.moveToFirst()) cursor.getString(idx) else null
                    } ?: "Course_PDF.pdf"

                    val pfd: ParcelFileDescriptor? = resolver.openFileDescriptor(uri, "r")
                    val pageCount = pfd?.use { descriptor ->
                        PdfRenderer(descriptor).use { renderer -> renderer.pageCount }
                    } ?: 0

                    Triple(uri.toString(), name, pageCount)
                }
            }
            result.onSuccess { (uriStr, name, pages) ->
                if (pages > 0) {
                    prefsRepo.setImportedPdf(uriStr, name, pages)
                }
            }
        }
    }

    fun updateLastPdfPage(pageIndex: Int) {
        viewModelScope.launch { prefsRepo.setLastPdfPageIndex(pageIndex) }
    }

    fun togglePdfBookmark(pageIndex: Int, currentlyBookmarked: Boolean) {
        viewModelScope.launch { repository.togglePdfBookmark(pageIndex, currentlyBookmarked) }
    }

    class Factory(
        private val repository: LearningRepository,
        private val prefsRepo: UserPreferencesRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository, prefsRepo) as T
        }
    }
}
