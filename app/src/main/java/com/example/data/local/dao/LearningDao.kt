package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.LessonCardEntity
import com.example.data.local.entity.LessonEntity
import com.example.data.local.entity.LessonProgressEntity
import com.example.data.local.entity.PdfBookmarkEntity
import com.example.data.local.entity.QuizQuestionEntity
import com.example.data.local.entity.QuizResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LearningDao {

    @Query("SELECT COUNT(*) FROM lessons")
    suspend fun getLessonCount(): Int

    @Query("SELECT * FROM lessons ORDER BY orderIndex ASC")
    fun getAllLessons(): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lessons ORDER BY orderIndex ASC")
    suspend fun getAllLessonsOnce(): List<LessonEntity>

    @Query("SELECT * FROM lessons WHERE id = :lessonId LIMIT 1")
    fun getLessonById(lessonId: String): Flow<LessonEntity?>

    @Query("SELECT * FROM lessons WHERE categoryId = :categoryId ORDER BY orderIndex ASC")
    fun getLessonsByCategory(categoryId: String): Flow<List<LessonEntity>>

    @Query(
        """
        SELECT DISTINCT l.* FROM lessons l
        LEFT JOIN lesson_cards c ON l.id = c.lessonId
        WHERE l.titleEn LIKE '%' || :query || '%'
           OR l.titleBn LIKE '%' || :query || '%'
           OR l.categoryTitleEn LIKE '%' || :query || '%'
           OR l.categoryTitleBn LIKE '%' || :query || '%'
           OR l.bengaliExplanation LIKE '%' || :query || '%'
           OR c.primaryText LIKE '%' || :query || '%'
           OR c.bengaliMeaning LIKE '%' || :query || '%'
           OR c.exampleEn LIKE '%' || :query || '%'
        ORDER BY l.orderIndex ASC
        """
    )
    fun searchLessons(query: String): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lesson_cards WHERE lessonId = :lessonId ORDER BY cardOrder ASC")
    fun getCardsForLesson(lessonId: String): Flow<List<LessonCardEntity>>

    @Query("SELECT * FROM lesson_cards WHERE cardType = :cardType ORDER BY id ASC")
    fun getCardsByType(cardType: String): Flow<List<LessonCardEntity>>

    @Query("SELECT * FROM quiz_questions ORDER BY id ASC")
    fun getAllQuizQuestions(): Flow<List<QuizQuestionEntity>>

    @Query("SELECT * FROM quiz_questions WHERE categoryId = :categoryId ORDER BY id ASC")
    fun getQuizQuestionsByCategory(categoryId: String): Flow<List<QuizQuestionEntity>>

    @Query("SELECT * FROM lesson_progress")
    fun getAllProgress(): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId LIMIT 1")
    suspend fun getProgressOnce(lessonId: String): LessonProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProgress(progress: LessonProgressEntity)

    @Query("SELECT * FROM quiz_results ORDER BY timestamp DESC")
    fun getAllQuizResults(): Flow<List<QuizResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizResult(result: QuizResultEntity)

    @Query("SELECT * FROM pdf_page_bookmarks ORDER BY pageIndex ASC")
    fun getPdfBookmarks(): Flow<List<PdfBookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPdfBookmark(bookmark: PdfBookmarkEntity)

    @Query("DELETE FROM pdf_page_bookmarks WHERE pageIndex = :pageIndex")
    suspend fun deletePdfBookmark(pageIndex: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<LessonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(cards: List<LessonCardEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizQuestions(questions: List<QuizQuestionEntity>)
}
