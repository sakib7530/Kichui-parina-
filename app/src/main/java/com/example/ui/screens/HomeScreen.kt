package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.rounded.AutoStories
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.PictureAsPdf
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Quiz
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Spellcheck
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ui.components.AppLogoBadge
import com.example.ui.components.LessonListCard
import com.example.ui.components.ProgressSummaryCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.LocalLearningPalette
import com.example.ui.viewmodel.AppDashboardState

private data class HomeMenuItem(
    val titleEn: String,
    val titleBn: String,
    val icon: ImageVector,
    val colorIndex: Int,
    val testTag: String,
    val onClick: () -> Unit
)

@Composable
fun HomeScreen(
    state: AppDashboardState,
    onOpenLesson: (String) -> Unit,
    onOpenAllLessons: () -> Unit,
    onOpenVocabulary: () -> Unit,
    onOpenQuiz: (String) -> Unit,
    onOpenProgress: () -> Unit,
    onOpenPdfReader: () -> Unit,
    onOpenSettings: () -> Unit,
    onToggleBookmark: (String) -> Unit
) {
    val palette = LocalLearningPalette.current

    val menuItems = listOf(
        HomeMenuItem("All Lessons", "সবগুলো পাঠ", Icons.AutoMirrored.Rounded.MenuBook, 0, "home_all_lessons", onOpenAllLessons),
        HomeMenuItem("My Progress", "আমার অগ্রগতি", Icons.Rounded.TrendingUp, 1, "home_my_progress", onOpenProgress),
        HomeMenuItem("Daily Practice", "দৈনিক অনুশীলন", Icons.Rounded.Bolt, 2, "home_daily_practice") { onOpenQuiz("all") },
        HomeMenuItem("Vocabulary", "শব্দভাণ্ডার A–Z", Icons.Rounded.Spellcheck, 3, "home_vocabulary", onOpenVocabulary),
        HomeMenuItem("Quiz", "কুইজ পরীক্ষা", Icons.Rounded.Quiz, 4, "home_quiz") { onOpenQuiz("all") },
        HomeMenuItem("Story Reading", "গল্প পড়া", Icons.Rounded.AutoStories, 5, "home_story_reading") { onOpenLesson("lesson_story_reading") },
        HomeMenuItem("Course PDF", "PDF ও রিপোর্ট", Icons.Rounded.PictureAsPdf, 6, "home_pdf_reader", onOpenPdfReader),
        HomeMenuItem("Settings", "সেটিংস", Icons.Rounded.Settings, 7, "home_settings", onOpenSettings)
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 760.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // Top App Identity Header + Welcome
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        AppLogoBadge(sizeDp = 52)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "কিছুই পারিনা – English",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "স্বাগতম! শূন্য থেকে সহজ বাংলায় ইংরেজি শিখুন",
                                style = MaterialTheme.typography.bodyMedium,
                                color = palette.sky
                            )
                        }
                    }
                    IconButton(
                        onClick = onOpenSettings,
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("top_settings_button")
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Settings,
                            contentDescription = "সেটিংস",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }

            // Continue Learning Hero Card
            item {
                val continueItem = state.continueLesson
                Card(
                    onClick = {
                        continueItem?.let { onOpenLesson(it.lesson.id) } ?: onOpenAllLessons()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("continue_learning_card"),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, palette.gold.copy(alpha = 0.55f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.surfaceVariant,
                                        MaterialTheme.colorScheme.surface
                                    )
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = palette.goldContainer
                            ) {
                                Text(
                                    text = "CONTINUE LEARNING • পড়া চালিয়ে যান",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = palette.gold,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                                )
                            }
                            Text(
                                text = continueItem?.lesson?.titleEn ?: "Start Your First Lesson",
                                style = MaterialTheme.typography.headlineLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = continueItem?.lesson?.titleBn ?: "ইংরেজি ২৬টি বর্ণ দিয়ে শুরু করুন",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Button(
                                onClick = {
                                    continueItem?.let { onOpenLesson(it.lesson.id) } ?: onOpenAllLessons()
                                },
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("continue_learning_button"),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = palette.gold,
                                    contentColor = Color(0xFF081229)
                                )
                            ) {
                                Icon(Icons.Rounded.PlayArrow, contentDescription = null)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "এখনই শুরু করুন",
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        }
                    }
                }
            }

            // Progress Bar Card
            item {
                ProgressSummaryCard(
                    completedCount = state.completedCount,
                    totalCount = state.totalCount,
                    progressPercent = state.progressPercent
                )
            }

            // Alphabet Colorful Quick Strip
            item {
                SectionHeader(
                    titleEn = "Colourful Alphabet Cards",
                    titleBn = "রঙিন বর্ণমালা ও মূল বিষয়",
                    trailing = {
                        TextButton(onClick = { onOpenLesson("lesson_alphabets") }) {
                            Text("সবগুলো দেখুন")
                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                val sampleLetters = listOf(
                    "A" to "Apple (আপেল)",
                    "B" to "Book (বই)",
                    "C" to "Cat (বিড়াল)",
                    "D" to "Door (দরজা)",
                    "E" to "Egg (ডিম)",
                    "F" to "Fish (মাছ)",
                    "G" to "Girl (মেয়ে)",
                    "H" to "House (বাড়ি)"
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(sampleLetters.size) { idx ->
                        val (letter, word) = sampleLetters[idx]
                        val accent = palette.cardAccents[idx % palette.cardAccents.size]
                        Card(
                            onClick = { onOpenLesson("lesson_alphabets") },
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = accent)
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(104.dp)
                                    .padding(14.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "$letter ${letter.lowercase()}",
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = word,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White.copy(alpha = 0.92f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }

            // Home Dashboard Quick Modules Grid (2 columns)
            item {
                SectionHeader(
                    titleEn = "Learning Dashboard",
                    titleBn = "সব শেখার বিভাগ এক জায়গায়"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    menuItems.chunked(2).forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            rowItems.forEach { menu ->
                                val accent = palette.cardAccents[menu.colorIndex % palette.cardAccents.size]
                                Card(
                                    onClick = menu.onClick,
                                    modifier = Modifier
                                        .weight(1f)
                                        .minimumInteractiveComponentSize()
                                        .testTag(menu.testTag),
                                    shape = RoundedCornerShape(18.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surface
                                    ),
                                    border = BorderStroke(1.dp, accent.copy(alpha = 0.35f))
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(40.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(accent.copy(alpha = 0.16f)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = menu.icon,
                                                contentDescription = menu.titleEn,
                                                tint = accent
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = menu.titleEn,
                                                style = MaterialTheme.typography.labelLarge,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Text(
                                                text = menu.titleBn,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Key Featured Lessons Preview
            item {
                SectionHeader(
                    titleEn = "Core Lessons",
                    titleBn = "শুরু করার জন্য সেরা পাঠসমূহ",
                    trailing = {
                        TextButton(onClick = onOpenAllLessons) {
                            Text("সব দেখুন (${state.totalCount})")
                        }
                    }
                )
            }

            items(state.lessons.take(5), key = { it.lesson.id }) { item ->
                LessonListCard(
                    item = item,
                    onClick = { onOpenLesson(item.lesson.id) },
                    onToggleBookmark = { onToggleBookmark(item.lesson.id) }
                )
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }
        }
    }
}
