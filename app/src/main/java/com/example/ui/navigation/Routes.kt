package com.example.ui.navigation

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val LESSONS = "lessons"
    const val VOCABULARY = "vocabulary"
    const val QUIZ = "quiz"
    const val PROGRESS = "progress"
    const val SETTINGS = "settings"
    const val PDF_READER = "pdf_reader"
    const val VERIFICATION_REPORT = "verification_report"
    const val LESSON_DETAIL = "lesson_detail/{lessonId}"

    fun lessonDetail(lessonId: String): String = "lesson_detail/$lessonId"
    fun quizWithCategory(categoryId: String = "all"): String = "quiz/$categoryId"
    const val QUIZ_ROUTE_PATTERN = "quiz/{categoryId}"
}
