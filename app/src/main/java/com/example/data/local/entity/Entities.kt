package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class LessonEntity(
    @PrimaryKey val id: String,
    val categoryId: String,
    val categoryTitleEn: String,
    val categoryTitleBn: String,
    val orderIndex: Int,
    val titleEn: String,
    val titleBn: String,
    val bengaliExplanation: String,
    val contentSource: String,
    val pdfPageNote: String,
    val iconKey: String
)

@Entity(
    tableName = "lesson_cards",
    indices = [Index(value = ["lessonId"])]
)
data class LessonCardEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val lessonId: String,
    val cardOrder: Int,
    val cardType: String,
    val primaryText: String,
    val secondaryText: String,
    val bengaliMeaning: String,
    val exampleEn: String,
    val exampleBn: String,
    val cardColorIndex: Int
)

@Entity(
    tableName = "quiz_questions",
    indices = [Index(value = ["categoryId"]), Index(value = ["lessonId"])]
)
data class QuizQuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val lessonId: String,
    val categoryId: String,
    val questionEn: String,
    val questionBn: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctOptionIndex: Int,
    val explanationBn: String,
    val isGeneratedPractice: Boolean = true
)

@Entity(tableName = "lesson_progress")
data class LessonProgressEntity(
    @PrimaryKey val lessonId: String,
    val isCompleted: Boolean = false,
    val isBookmarked: Boolean = false,
    val lastOpenedAt: Long = 0L
)

@Entity(tableName = "quiz_results")
data class QuizResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val categoryId: String,
    val categoryTitle: String,
    val score: Int,
    val totalQuestions: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "pdf_page_bookmarks")
data class PdfBookmarkEntity(
    @PrimaryKey val pageIndex: Int,
    val note: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
