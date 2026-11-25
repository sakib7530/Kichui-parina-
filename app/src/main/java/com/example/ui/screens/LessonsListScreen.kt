package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.SearchOff
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.components.LessonListCard
import com.example.ui.components.SectionHeader
import com.example.ui.viewmodel.AppDashboardState
import com.example.ui.viewmodel.LessonFilter

@Composable
fun LessonsListScreen(
    state: AppDashboardState,
    onSearchChange: (String) -> Unit,
    onFilterChange: (LessonFilter) -> Unit,
    onOpenLesson: (String) -> Unit,
    onToggleBookmark: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 760.dp)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            SectionHeader(
                titleEn = "All English Lessons",
                titleBn = "সকল অধ্যায় ও পাঠ (${state.totalCount}টি লেসন)"
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("lesson_search_input"),
                placeholder = {
                    Text("পাঠ, শব্দ বা বাংলা অর্থ খুঁজুন...")
                },
                leadingIcon = {
                    Icon(Icons.Rounded.Search, contentDescription = "অনুসন্ধান")
                },
                trailingIcon = {
                    if (state.searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchChange("") }) {
                            Icon(Icons.Rounded.Clear, contentDescription = "মুছুন")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            val filters = listOf(
                LessonFilter.ALL to "সব (${state.totalCount})",
                LessonFilter.BOOKMARKED to "পছন্দের (${state.bookmarkedCount})",
                LessonFilter.COMPLETED to "সম্পন্ন (${state.completedCount})",
                LessonFilter.REMAINING to "বাকি (${(state.totalCount - state.completedCount).coerceAtLeast(0)})"
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filters) { (filter, label) ->
                    FilterChip(
                        selected = state.activeFilter == filter,
                        onClick = { onFilterChange(filter) },
                        label = { Text(label) },
                        modifier = Modifier.testTag("filter_chip_${filter.name.lowercase()}")
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (state.filteredLessons.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Rounded.SearchOff,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.height(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "কোনো পাঠ পাওয়া যায়নি",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "অন্য শব্দ দিয়ে খুঁজে দেখুন বা ফিল্টার পরিবর্তন করুন",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.filteredLessons, key = { it.lesson.id }) { item ->
                        LessonListCard(
                            item = item,
                            onClick = { onOpenLesson(item.lesson.id) },
                            onToggleBookmark = { onToggleBookmark(item.lesson.id) }
                        )
                    }
                }
            }
        }
    }
}
