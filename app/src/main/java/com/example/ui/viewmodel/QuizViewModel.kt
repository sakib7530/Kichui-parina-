package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.local.entity.QuizQuestionEntity
import com.example.data.repository.LearningRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class QuizUiState(
    val isLoading: Boolean = true,
    val selectedCategoryId: String = "all",
    val selectedCategoryTitle: String = "সকল অধ্যায়ের অনুশীলন",
    val questions: List<QuizQuestionEntity> = emptyList(),
    val currentIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isSubmitted: Boolean = false,
    val score: Int = 0,
    val isFinished: Boolean = false
) {
    val currentQuestion: QuizQuestionEntity?
        get() = questions.getOrNull(currentIndex)
}

class QuizViewModel(
    private val repository: LearningRepository,
    initialCategoryId: String = "all"
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState(selectedCategoryId = initialCategoryId))
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        loadQuiz(initialCategoryId, "সকল অধ্যায়ের অনুশীলন")
    }

    fun loadQuiz(categoryId: String, categoryTitle: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val loaded = repository.getQuizQuestions(categoryId).first()
            val finalQuestions = if (loaded.isEmpty()) {
                repository.getQuizQuestions("all").first()
            } else {
                loaded
            }
            _uiState.value = QuizUiState(
                isLoading = false,
                selectedCategoryId = categoryId,
                selectedCategoryTitle = categoryTitle,
                questions = finalQuestions,
                currentIndex = 0,
                selectedOptionIndex = null,
                isSubmitted = false,
                score = 0,
                isFinished = false
            )
        }
    }

    fun selectOption(index: Int) {
        val current = _uiState.value
        if (current.isSubmitted || current.isFinished) return
        _uiState.update { it.copy(selectedOptionIndex = index) }
    }

    fun submitAnswer() {
        val current = _uiState.value
        val question = current.currentQuestion ?: return
        val selected = current.selectedOptionIndex ?: return
        if (current.isSubmitted) return

        val isCorrect = selected == question.correctOptionIndex
        _uiState.update {
            it.copy(
                isSubmitted = true,
                score = if (isCorrect) it.score + 1 else it.score
            )
        }
    }

    fun nextQuestion() {
        val current = _uiState.value
        if (!current.isSubmitted) return
        if (current.currentIndex + 1 >= current.questions.size) {
            _uiState.update { it.copy(isFinished = true) }
            viewModelScope.launch {
                repository.saveQuizResult(
                    categoryId = current.selectedCategoryId,
                    categoryTitle = current.selectedCategoryTitle,
                    score = current.score,
                    totalQuestions = current.questions.size
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    currentIndex = it.currentIndex + 1,
                    selectedOptionIndex = null,
                    isSubmitted = false
                )
            }
        }
    }

    fun restartQuiz() {
        val current = _uiState.value
        loadQuiz(current.selectedCategoryId, current.selectedCategoryTitle)
    }

    class Factory(
        private val repository: LearningRepository,
        private val initialCategoryId: String = "all"
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return QuizViewModel(repository, initialCategoryId) as T
        }
    }
}
