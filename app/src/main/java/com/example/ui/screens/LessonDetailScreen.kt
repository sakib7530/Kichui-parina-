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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Quiz
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.local.entity.LessonCardEntity
import com.example.ui.components.ColorfulLearningCard
import com.example.ui.components.HonestSourceBanner
import com.example.ui.theme.LocalLearningPalette
import com.example.ui.viewmodel.LessonDetailUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(
    uiState: LessonDetailUiState,
    onBack: () -> Unit,
    onNavigateToLesson: (String) -> Unit,
    onToggleCompleted: () -> Unit,
    onToggleBookmark: () -> Unit,
    onStartCategoryQuiz: (String) -> Unit
) {
    val palette = LocalLearningPalette.current

    when (uiState) {
        LessonDetailUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = palette.gold)
            }
        }
        is LessonDetailUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(uiState.message, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = onBack) { Text("ফিরে যান") }
                }
            }
        }
        is LessonDetailUiState.Success -> {
            val lesson = uiState.lesson
            var selectedAlphabetCard by remember(lesson.id) {
                mutableStateOf<LessonCardEntity?>(null)
            }

            Scaffold(
                containerColor = MaterialTheme.colorScheme.background,
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        navigationIcon = {
                            IconButton(
                                onClick = onBack,
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("lesson_back_button")
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                    contentDescription = "পিছনে যান"
                                )
                            }
                        },
                        title = {
                            Column {
                                Text(
                                    text = "পাঠ ${uiState.currentPosition} / ${uiState.totalLessons} • ${lesson.categoryTitleEn}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = palette.sky,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = lesson.titleEn,
                                    style = MaterialTheme.typography.titleMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        },
                        actions = {
                            IconButton(
                                onClick = onToggleBookmark,
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("lesson_detail_bookmark_button")
                            ) {
                                Icon(
                                    imageVector = if (uiState.isBookmarked) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
                                    contentDescription = if (uiState.isBookmarked) "বুকমার্ক সরান" else "বুকমার্ক করুন",
                                    tint = if (uiState.isBookmarked) palette.gold else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    )
                },
                bottomBar = {
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        tonalElevation = 6.dp,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .navigationBarsPadding()
                                .padding(horizontal = 12.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = { uiState.previousLessonId?.let(onNavigateToLesson) },
                                enabled = uiState.previousLessonId != null,
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("previous_lesson_button"),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "আগের পাঠ")
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("আগেরটি", maxLines = 1)
                            }

                            Button(
                                onClick = onToggleCompleted,
                                modifier = Modifier
                                    .weight(1f)
                                    .minimumInteractiveComponentSize()
                                    .testTag("mark_completed_button"),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (uiState.isCompleted) palette.success else palette.gold,
                                    contentColor = Color(0xFF081229)
                                )
                            ) {
                                Icon(
                                    imageVector = if (uiState.isCompleted) Icons.Rounded.CheckCircle else Icons.Rounded.RadioButtonUnchecked,
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (uiState.isCompleted) "সম্পন্ন" else "সম্পন্ন করুন",
                                    maxLines = 1,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            OutlinedButton(
                                onClick = { uiState.nextLessonId?.let(onNavigateToLesson) },
                                enabled = uiState.nextLessonId != null,
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("next_lesson_button"),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text("পরেরটি", maxLines = 1)
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(Icons.AutoMirrored.Rounded.ArrowForward, contentDescription = "পরের পাঠ")
                            }
                        }
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.TopCenter
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .widthIn(max = 760.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        item {
                            Card(
                                shape = RoundedCornerShape(22.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                border = BorderStroke(1.dp, palette.gold.copy(alpha = 0.45f))
                            ) {
                                Column(
                                    modifier = Modifier.padding(18.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(
                                        text = lesson.titleBn,
                                        style = MaterialTheme.typography.headlineMedium,
                                        color = palette.gold
                                    )
                                    Text(
                                        text = lesson.bengaliExplanation,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                        item {
                            HonestSourceBanner(text = lesson.pdfPageNote)
                        }

                        if (lesson.id == "lesson_alphabets" || lesson.id == "lesson_pronunciation") {
                            item {
                                Text(
                                    text = "রঙিন বর্ণ কার্ড (ট্যাপ করে বড় করে দেখুন)",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    uiState.cards.chunked(4).forEach { row ->
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            row.forEach { card ->
                                                val accent = palette.cardAccents[card.cardColorIndex % palette.cardAccents.size]
                                                Card(
                                                    onClick = { selectedAlphabetCard = card },
                                                    modifier = Modifier
                                                        .weight(1f)
                                                        .minimumInteractiveComponentSize(),
                                                    shape = RoundedCornerShape(16.dp),
                                                    colors = CardDefaults.cardColors(containerColor = accent)
                                                ) {
                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(vertical = 12.dp, horizontal = 6.dp),
                                                        horizontalAlignment = Alignment.CenterHorizontally
                                                    ) {
                                                        Text(
                                                            text = card.primaryText.substringBefore(" ("),
                                                            style = MaterialTheme.typography.titleLarge,
                                                            color = Color.White,
                                                            fontWeight = FontWeight.Bold,
                                                            maxLines = 1
                                                        )
                                                        Text(
                                                            text = card.bengaliMeaning.substringAfter("— ").take(10),
                                                            style = MaterialTheme.typography.labelSmall,
                                                            color = Color.White.copy(alpha = 0.9f),
                                                            maxLines = 1,
                                                            overflow = TextOverflow.Ellipsis
                                                        )
                                                    }
                                                }
                                            }
                                            repeat(4 - row.size) {
                                                Spacer(modifier = Modifier.weight(1f))
                                            }
                                        }
                                    }
                                }
                            }

                            selectedAlphabetCard?.let { focused ->
                                item {
                                    ColorfulLearningCard(card = focused)
                                }
                            }
                        }

                        items(uiState.cards, key = { it.id }) { card ->
                            ColorfulLearningCard(card = card)
                        }

                        item {
                            OutlinedButton(
                                onClick = { onStartCategoryQuiz(lesson.categoryId) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .minimumInteractiveComponentSize()
                                    .testTag("lesson_practice_quiz_button"),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Icon(Icons.Rounded.Quiz, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("এই অধ্যায়ের অনুশীলন কুইজ দিন")
                            }
                        }
                    }
                }
            }
        }
    }
}
