package com.example.data.repository

import com.example.data.local.dao.LearningDao
import com.example.data.local.entity.LessonCardEntity
import com.example.data.local.entity.LessonEntity
import com.example.data.local.entity.LessonProgressEntity
import com.example.data.local.entity.PdfBookmarkEntity
import com.example.data.local.entity.QuizQuestionEntity
import com.example.data.local.entity.QuizResultEntity
import com.example.data.preferences.UserPreferencesRepository
import com.example.data.seed.AlphabetAndVocabSeed
import com.example.data.seed.PatternsAndGrammarSeed
import com.example.data.seed.QuestionsDialoguesStoriesSeed
import com.example.data.seed.QuizSeed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

data class LessonWithProgress(
    val lesson: LessonEntity,
    val isCompleted: Boolean,
    val isBookmarked: Boolean,
    val lastOpenedAt: Long
)

class LearningRepository(
    private val dao: LearningDao,
    private val prefsRepo: UserPreferencesRepository
) {
    private val seedMutex = Mutex()

    val allLessons: Flow<List<LessonEntity>> = dao.getAllLessons()
    val allProgress: Flow<List<LessonProgressEntity>> = dao.getAllProgress()
    val quizResults: Flow<List<QuizResultEntity>> = dao.getAllQuizResults()
    val pdfBookmarks: Flow<List<PdfBookmarkEntity>> = dao.getPdfBookmarks()

    suspend fun ensureSeeded() = withContext(Dispatchers.IO) {
        seedMutex.withLock {
            if (dao.getLessonCount() == 0) {
                val lessons = AlphabetAndVocabSeed.lessons() +
                    PatternsAndGrammarSeed.lessons() +
                    QuestionsDialoguesStoriesSeed.lessons()

                val cards = AlphabetAndVocabSeed.cards() +
                    PatternsAndGrammarSeed.cards() +
                    QuestionsDialoguesStoriesSeed.cards()

                val quizzes = QuizSeed.questions()

                dao.insertLessons(lessons)
                dao.insertCards(cards)
                dao.insertQuizQuestions(quizzes)
            }
        }
    }

    fun observeLessonsWithProgress(): Flow<List<LessonWithProgress>> {
        return kotlinx.coroutines.flow.combine(allLessons, allProgress) { lessons, progressList ->
            val progressMap = progressList.associateBy { it.lessonId }
            lessons.map { lesson ->
                val p = progressMap[lesson.id]
                LessonWithProgress(
                    lesson = lesson,
                    isCompleted = p?.isCompleted == true,
                    isBookmarked = p?.isBookmarked == true,
                    lastOpenedAt = p?.lastOpenedAt ?: 0L
                )
            }
        }
    }

    fun searchLessons(query: String): Flow<List<LessonEntity>> {
        return if (query.isBlank()) allLessons else dao.searchLessons(query.trim())
    }

    fun getLessonById(lessonId: String): Flow<LessonEntity?> = dao.getLessonById(lessonId)

    fun getCardsForLesson(lessonId: String): Flow<List<LessonCardEntity>> =
        dao.getCardsForLesson(lessonId)

    fun getCardsByType(cardType: String): Flow<List<LessonCardEntity>> =
        dao.getCardsByType(cardType)

    fun getQuizQuestions(categoryId: String? = null): Flow<List<QuizQuestionEntity>> {
        return if (categoryId.isNullOrBlank() || categoryId == "all") {
            dao.getAllQuizQuestions()
        } else {
            dao.getQuizQuestionsByCategory(categoryId)
        }
    }

    suspend fun markLessonOpened(lessonId: String) = withContext(Dispatchers.IO) {
        val current = dao.getProgressOnce(lessonId)
        val updated = (current ?: LessonProgressEntity(lessonId = lessonId)).copy(
            lastOpenedAt = System.currentTimeMillis()
        )
        dao.upsertProgress(updated)
        prefsRepo.setLastOpenedLessonId(lessonId)
    }

    suspend fun toggleCompleted(lessonId: String) = withContext(Dispatchers.IO) {
        val current = dao.getProgressOnce(lessonId)
        val updated = (current ?: LessonProgressEntity(lessonId = lessonId)).copy(
            isCompleted = !(current?.isCompleted ?: false),
            lastOpenedAt = System.currentTimeMillis()
        )
        dao.upsertProgress(updated)
    }

    suspend fun toggleBookmark(lessonId: String) = withContext(Dispatchers.IO) {
        val current = dao.getProgressOnce(lessonId)
        val updated = (current ?: LessonProgressEntity(lessonId = lessonId)).copy(
            isBookmarked = !(current?.isBookmarked ?: false)
        )
        dao.upsertProgress(updated)
    }

    suspend fun saveQuizResult(
        categoryId: String,
        categoryTitle: String,
        score: Int,
        totalQuestions: Int
    ) = withContext(Dispatchers.IO) {
        dao.insertQuizResult(
            QuizResultEntity(
                categoryId = categoryId,
                categoryTitle = categoryTitle,
                score = score,
                totalQuestions = totalQuestions
            )
        )
    }

    suspend fun togglePdfBookmark(pageIndex: Int, isCurrentlyBookmarked: Boolean) =
        withContext(Dispatchers.IO) {
            if (isCurrentlyBookmarked) {
                dao.deletePdfBookmark(pageIndex)
            } else {
                dao.insertPdfBookmark(
                    PdfBookmarkEntity(
                        pageIndex = pageIndex,
                        note = "পৃষ্ঠা ${pageIndex + 1}"
                    )
                )
            }
        }

    fun observeAdjacentLessons(lessonId: String): Flow<Pair<String?, String?>> {
        return allLessons.map { list ->
            val idx = list.indexOfFirst { it.id == lessonId }
            if (idx == -1) {
                null to null
            } else {
                val prev = list.getOrNull(idx - 1)?.id
                val next = list.getOrNull(idx + 1)?.id
                prev to next
            }
        }
    }
}
