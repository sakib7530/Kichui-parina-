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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.components.ColorfulLearningCard
import com.example.ui.components.SectionHeader
import com.example.ui.viewmodel.AppDashboardState

@Composable
fun VocabularyScreen(
    state: AppDashboardState
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedLetter by remember { mutableStateOf("All") }

    val activeSource = if (selectedTab == 0) state.vocabCards else state.verbCards
    val letters = listOf("All") + ('A'..'Z').map { it.toString() }

    val filteredCards = remember(activeSource, searchQuery, selectedLetter) {
        activeSource.filter { card ->
            val matchesLetter = selectedLetter == "All" ||
                card.primaryText.startsWith(selectedLetter, ignoreCase = true)
            val matchesQuery = searchQuery.isBlank() ||
                card.primaryText.contains(searchQuery, ignoreCase = true) ||
                card.bengaliMeaning.contains(searchQuery, ignoreCase = true) ||
                card.exampleEn.contains(searchQuery, ignoreCase = true)
            matchesLetter && matchesQuery
        }
    }

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
                titleEn = "Vocabulary & Verbs A–Z",
                titleBn = "ইংরেজি শব্দভাণ্ডার ও বেসিক ক্রিয়াপদ"
            )

            Spacer(modifier = Modifier.height(10.dp))

            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("শব্দভাণ্ডার (${state.vocabCards.size})") },
                    modifier = Modifier.testTag("vocab_tab_words")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Verbs A–Z (${state.verbCards.size})") },
                    modifier = Modifier.testTag("vocab_tab_verbs")
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("vocab_search_input"),
                placeholder = { Text("ইংরেজি বা বাংলা শব্দ খুঁজুন...") },
                leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = "অনুসন্ধান") },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Rounded.Clear, contentDescription = "মুছুন")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(letters) { letter ->
                    FilterChip(
                        selected = selectedLetter == letter,
                        onClick = { selectedLetter = letter },
                        label = { Text(letter) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (filteredCards.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "এই অক্ষরে বা অনুসন্ধানে কোনো শব্দ পাওয়া যায়নি।",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filteredCards, key = { it.id }) { card ->
                        ColorfulLearningCard(card = card)
                    }
                }
            }
        }
    }
}
