package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.local.entity.LessonCardEntity
import com.example.data.local.entity.LessonEntity
import com.example.data.repository.LearningRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface LessonDetailUiState {
    data object Loading : LessonDetailUiState
    data class Success(
        val lesson: LessonEntity,
        val cards: List<LessonCardEntity>,
        val isCompleted: Boolean,
        val isBookmarked: Boolean,
        val previousLessonId: String?,
        val nextLessonId: String?,
        val currentPosition: Int,
        val totalLessons: Int
    ) : LessonDetailUiState
    data class Error(val message: String) : LessonDetailUiState
}

class LessonDetailViewModel(
    private val lessonId: String,
    private val repository: LearningRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.markLessonOpened(lessonId)
        }
    }

    val uiState: StateFlow<LessonDetailUiState> = combine(
        repository.getLessonById(lessonId),
        repository.getCardsForLesson(lessonId),
        repository.allProgress,
        repository.allLessons
    ) { lesson, cards, progressList, allLessons ->
        if (lesson == null) {
            LessonDetailUiState.Error("পাঠটি খুঁজে পাওয়া যায়নি।")
        } else {
            val progress = progressList.firstOrNull { it.lessonId == lessonId }
            val index = allLessons.indexOfFirst { it.id == lessonId }
            val prevId = if (index > 0) allLessons[index - 1].id else null
            val nextId = if (index != -1 && index < allLessons.lastIndex) allLessons[index + 1].id else null
            LessonDetailUiState.Success(
                lesson = lesson,
                cards = cards,
                isCompleted = progress?.isCompleted == true,
                isBookmarked = progress?.isBookmarked == true,
                previousLessonId = prevId,
                nextLessonId = nextId,
                currentPosition = if (index >= 0) index + 1 else 1,
                totalLessons = allLessons.size.coerceAtLeast(1)
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LessonDetailUiState.Loading
    )

    fun toggleCompleted() {
        viewModelScope.launch { repository.toggleCompleted(lessonId) }
    }

    fun toggleBookmark() {
        viewModelScope.launch { repository.toggleBookmark(lessonId) }
    }

    class Factory(
        private val lessonId: String,
        private val repository: LearningRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LessonDetailViewModel(lessonId, repository) as T
        }
    }
}
