package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.components.HonestSourceBanner
import com.example.ui.components.SectionHeader
import com.example.ui.theme.LocalLearningPalette
import com.example.ui.viewmodel.QuizUiState

@Composable
fun QuizScreen(
    uiState: QuizUiState,
    onSelectCategory: (String, String) -> Unit,
    onSelectOption: (Int) -> Unit,
    onSubmitAnswer: () -> Unit,
    onNextQuestion: () -> Unit,
    onRestartQuiz: () -> Unit
) {
    val palette = LocalLearningPalette.current
    val categories = listOf(
        "all" to "সব বিষয়",
        "alphabets" to "Alphabets",
        "vocabulary_az" to "Vocabulary",
        "verbs_az" to "Verbs",
        "sentence_patterns" to "Patterns",
        "i_am_want_have" to "I am/want/have",
        "wh_questions" to "WH Questions",
        "prepositions" to "Prepositions",
        "self_introduction" to "Self Intro"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 760.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                SectionHeader(
                    titleEn = "Practice Quiz",
                    titleBn = "বহুনির্বাচনী প্রশ্ন ও তাৎক্ষণিক উত্তর যাচাই"
                )
            }

            item {
                HonestSourceBanner(
                    text = "নোট: এই অনুশীলন কুইজগুলো বাংলা-ইংরেজি চর্চার জন্য তৈরি করা হয়েছে (মূল ৩২৩ পৃষ্ঠার PDF থেকে সংগৃহীত নয়)।"
                )
            }

            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(categories) { (catId, label) ->
                        FilterChip(
                            selected = uiState.selectedCategoryId == catId,
                            onClick = { onSelectCategory(catId, label) },
                            label = { Text(label) }
                        )
                    }
                }
            }

            if (uiState.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = palette.gold)
                    }
                }
            } else if (uiState.isFinished) {
                item {
                    val total = uiState.questions.size.coerceAtLeast(1)
                    val pct = (uiState.score * 100) / total
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quiz_result_card"),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, palette.gold.copy(alpha = 0.6f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.EmojiEvents,
                                contentDescription = "কুইজ ফলাফল",
                                tint = palette.gold,
                                modifier = Modifier.size(64.dp)
                            )
                            Text(
                                text = "অভিনন্দন! কুইজ সম্পন্ন হয়েছে",
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "আপনার স্কোর: ${uiState.score} / $total ($pct%)",
                                style = MaterialTheme.typography.headlineLarge,
                                color = palette.gold
                            )
                            Text(
                                text = if (pct >= 70) "দারুণ হয়েছে! নিয়মিত অনুশীলন চালিয়ে যান।" else "ভালো চেষ্টা! পাঠগুলো আরেকবার পড়ে আবার কুইজ দিন।",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Button(
                                onClick = onRestartQuiz,
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("quiz_restart_button"),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = palette.gold,
                                    contentColor = Color(0xFF081229)
                                )
                            ) {
                                Icon(Icons.Rounded.Refresh, contentDescription = null)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("আবার খেলুন")
                            }
                        }
                    }
                }
            } else {
                val question = uiState.currentQuestion
                if (question != null) {
                    item {
                        val progress = (uiState.currentIndex + 1).toFloat() / uiState.questions.size.coerceAtLeast(1)
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "প্রশ্ন ${uiState.currentIndex + 1} / ${uiState.questions.size}",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = palette.sky
                                )
                                Text(
                                    text = "স্কোর: ${uiState.score}",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = palette.gold
                                )
                            }
                            LinearProgressIndicator(
                                progress = { progress },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(CircleShape),
                                color = palette.gold,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        }
                    }

                    item {
                        Card(
                            shape = RoundedCornerShape(22.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = question.questionEn,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = question.questionBn,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = palette.sky
                                )
                            }
                        }
                    }

                    val options = listOf(
                        question.optionA,
                        question.optionB,
                        question.optionC,
                        question.optionD
                    )
                    items(options.size) { index ->
                        val isSelected = uiState.selectedOptionIndex == index
                        val isCorrect = index == question.correctOptionIndex
                        val borderColor = when {
                            uiState.isSubmitted && isCorrect -> palette.success
                            uiState.isSubmitted && isSelected && !isCorrect -> MaterialTheme.colorScheme.error
                            isSelected -> palette.gold
                            else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.45f)
                        }
                        val bgColor = when {
                            uiState.isSubmitted && isCorrect -> palette.successContainer
                            uiState.isSubmitted && isSelected && !isCorrect -> MaterialTheme.colorScheme.errorContainer
                            isSelected -> palette.goldContainer
                            else -> MaterialTheme.colorScheme.surface
                        }

                        Card(
                            onClick = { onSelectOption(index) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .minimumInteractiveComponentSize()
                                .testTag("quiz_option_$index"),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = bgColor),
                            border = BorderStroke(1.5.dp, borderColor)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${'A' + index}.  ${options[index]}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )
                                if (uiState.isSubmitted && isCorrect) {
                                    Icon(
                                        Icons.Rounded.CheckCircle,
                                        contentDescription = "সঠিক উত্তর",
                                        tint = palette.success
                                    )
                                } else if (uiState.isSubmitted && isSelected && !isCorrect) {
                                    Icon(
                                        Icons.Rounded.Close,
                                        contentDescription = "ভুল উত্তর",
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }

                    if (uiState.isSubmitted) {
                        item {
                            val isRight = uiState.selectedOptionIndex == question.correctOptionIndex
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = if (isRight) palette.successContainer else MaterialTheme.colorScheme.errorContainer,
                                border = BorderStroke(
                                    1.dp,
                                    if (isRight) palette.success else MaterialTheme.colorScheme.error
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = if (isRight) "সঠিক উত্তর! দারুণ!" else "ভুল হয়েছে! সঠিক উত্তর: ${'A' + question.correctOptionIndex}",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = if (isRight) palette.success else MaterialTheme.colorScheme.error,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = question.explanationBn,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Button(
                            onClick = if (uiState.isSubmitted) onNextQuestion else onSubmitAnswer,
                            enabled = uiState.selectedOptionIndex != null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .minimumInteractiveComponentSize()
                                .testTag("quiz_action_button"),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = palette.gold,
                                contentColor = Color(0xFF081229)
                            )
                        ) {
                            Text(
                                text = when {
                                    !uiState.isSubmitted -> "উত্তর যাচাই করুন"
                                    uiState.currentIndex + 1 >= uiState.questions.size -> "ফলাফল দেখুন"
                                    else -> "পরবর্তী প্রশ্ন"
                                },
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
