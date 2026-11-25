package com.example

import com.example.data.seed.AlphabetAndVocabSeed
import com.example.data.seed.PatternsAndGrammarSeed
import com.example.data.seed.QuestionsDialoguesStoriesSeed
import com.example.data.seed.QuizSeed
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun `curriculum covers all 18 required categories and 19 lessons`() {
        val lessons = AlphabetAndVocabSeed.lessons() +
            PatternsAndGrammarSeed.lessons() +
            QuestionsDialoguesStoriesSeed.lessons()

        assertEquals(19, lessons.size)
        val categories = lessons.map { it.categoryId }.toSet()
        val expectedCategories = setOf(
            "alphabets",
            "capital_small",
            "pronunciation",
            "vocabulary_az",
            "verbs_az",
            "sentence_patterns",
            "i_am_want_have",
            "i_am_verbing",
            "tenses_patterns",
            "english_questions",
            "wh_questions",
            "advanced_questions",
            "dialogues",
            "prepositions",
            "adjectives",
            "sentence_making",
            "story_reading",
            "self_introduction"
        )
        assertTrue(categories.containsAll(expectedCategories))
    }

    @Test
    fun `every lesson has cards and honest starter source labeling`() {
        val lessons = AlphabetAndVocabSeed.lessons() +
            PatternsAndGrammarSeed.lessons() +
            QuestionsDialoguesStoriesSeed.lessons()
        val cards = AlphabetAndVocabSeed.cards() +
            PatternsAndGrammarSeed.cards() +
            QuestionsDialoguesStoriesSeed.cards()

        val cardsByLesson = cards.groupBy { it.lessonId }
        lessons.forEach { lesson ->
            assertEquals(AlphabetAndVocabSeed.SOURCE_STARTER, lesson.contentSource)
            assertTrue("Missing cards for ${lesson.id}", (cardsByLesson[lesson.id]?.size ?: 0) > 0)
        }
        assertEquals(26, cardsByLesson["lesson_alphabets"]?.size)
        assertEquals(26, cardsByLesson["lesson_pronunciation"]?.size)
    }

    @Test
    fun `quiz questions have valid answer indices and generated practice flag`() {
        val questions = QuizSeed.questions()
        assertEquals(18, questions.size)
        questions.forEach { q ->
            assertTrue(q.correctOptionIndex in 0..3)
            assertTrue(q.isGeneratedPractice)
            assertTrue(q.explanationBn.isNotBlank())
        }
    }
}
