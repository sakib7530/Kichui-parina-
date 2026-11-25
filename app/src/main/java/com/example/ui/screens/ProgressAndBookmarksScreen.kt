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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.components.LessonListCard
import com.example.ui.components.ProgressSummaryCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.LocalLearningPalette
import com.example.ui.viewmodel.AppDashboardState

@Composable
fun ProgressAndBookmarksScreen(
    state: AppDashboardState,
    onOpenLesson: (String) -> Unit,
    onToggleBookmark: (String) -> Unit
) {
    val palette = LocalLearningPalette.current
    val bookmarkedLessons = state.lessons.filter { it.isBookmarked }
    val completedLessons = state.lessons.filter { it.isCompleted }

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
                    titleEn = "My Progress & Bookmarks",
                    titleBn = "আপনার শেখার হিসাব, প্রিয় পাঠ ও কুইজ স্কোর"
                )
            }

            item {
                ProgressSummaryCard(
                    completedCount = state.completedCount,
                    totalCount = state.totalCount,
                    progressPercent = state.progressPercent
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StatMiniCard(
                        title = "সম্পন্ন পাঠ",
                        value = "${state.completedCount}",
                        icon = { Icon(Icons.Rounded.CheckCircle, contentDescription = null, tint = palette.success) },
                        modifier = Modifier.weight(1f)
                    )
                    StatMiniCard(
                        title = "সংরক্ষিত",
                        value = "${bookmarkedLessons.size}",
                        icon = { Icon(Icons.Rounded.Bookmark, contentDescription = null, tint = palette.gold) },
                        modifier = Modifier.weight(1f)
                    )
                    StatMiniCard(
                        title = "কুইজ দিয়েছেন",
                        value = "${state.quizResults.size}",
                        icon = { Icon(Icons.Rounded.EmojiEvents, contentDescription = null, tint = palette.sky) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                SectionHeader(
                    titleEn = "Bookmarked Lessons",
                    titleBn = "পছন্দের সংরক্ষিত পাঠসমূহ"
                )
            }

            if (bookmarkedLessons.isEmpty()) {
                item {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = "এখনো কোনো পাঠ বুকমার্ক করা হয়নি। যেকোনো পাঠের বুকমার্ক আইকনে চাপলে এখানে সংরক্ষিত হবে।",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            } else {
                items(bookmarkedLessons, key = { "bm_${it.lesson.id}" }) { item ->
                    LessonListCard(
                        item = item,
                        onClick = { onOpenLesson(item.lesson.id) },
                        onToggleBookmark = { onToggleBookmark(item.lesson.id) }
                    )
                }
            }

            item {
                SectionHeader(
                    titleEn = "Recent Quiz Scores",
                    titleBn = "সাম্প্রতিক কুইজ ফলাফল"
                )
            }

            if (state.quizResults.isEmpty()) {
                item {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = "এখনো কোনো কুইজ সম্পন্ন হয়নি। Quiz ট্যাবে গিয়ে প্রথম কুইজ দিন!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            } else {
                items(state.quizResults.take(8), key = { it.id }) { res ->
                    val pct = if (res.totalQuestions == 0) 0 else (res.score * 100) / res.totalQuestions
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(res.categoryTitle, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    "সঠিক উত্তর: ${res.score} / ${res.totalQuestions}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = palette.goldContainer
                            ) {
                                Text(
                                    text = "$pct%",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = palette.gold,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }
            }

            if (completedLessons.isNotEmpty()) {
                item {
                    SectionHeader(
                        titleEn = "Completed Lessons",
                        titleBn = "সম্পন্ন করা পাঠ (${completedLessons.size}টি)"
                    )
                }
                items(completedLessons, key = { "done_${it.lesson.id}" }) { item ->
                    LessonListCard(
                        item = item,
                        onClick = { onOpenLesson(item.lesson.id) },
                        onToggleBookmark = { onToggleBookmark(item.lesson.id) }
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun StatMiniCard(
    title: String,
    value: String,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.45f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            icon()
            Text(value, style = MaterialTheme.typography.headlineLarge)
            Text(
                title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
