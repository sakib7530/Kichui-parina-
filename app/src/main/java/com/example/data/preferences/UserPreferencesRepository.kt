package com.example.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.ui.theme.AppThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "kichui_parina_prefs")

data class UserSettings(
    val themeMode: AppThemeMode = AppThemeMode.DARK,
    val lastOpenedLessonId: String? = null,
    val dailyReminderEnabled: Boolean = false,
    val reminderHour: Int = 20,
    val reminderMinute: Int = 0,
    val importedPdfUri: String? = null,
    val importedPdfName: String? = null,
    val importedPdfPageCount: Int = 0,
    val lastPdfPageIndex: Int = 0
)

class UserPreferencesRepository(private val context: Context) {

    private object Keys {
        val THEME_MODE = stringPreferencesKey("theme_mode")
        val LAST_OPENED_LESSON = stringPreferencesKey("last_opened_lesson_id")
        val DAILY_REMINDER_ENABLED = booleanPreferencesKey("daily_reminder_enabled")
        val REMINDER_HOUR = intPreferencesKey("reminder_hour")
        val REMINDER_MINUTE = intPreferencesKey("reminder_minute")
        val IMPORTED_PDF_URI = stringPreferencesKey("imported_pdf_uri")
        val IMPORTED_PDF_NAME = stringPreferencesKey("imported_pdf_name")
        val IMPORTED_PDF_PAGE_COUNT = intPreferencesKey("imported_pdf_page_count")
        val LAST_PDF_PAGE_INDEX = intPreferencesKey("last_pdf_page_index")
    }

    val settings: Flow<UserSettings> = context.dataStore.data.map { prefs ->
        val themeStr = prefs[Keys.THEME_MODE] ?: AppThemeMode.DARK.name
        val theme = runCatching { AppThemeMode.valueOf(themeStr) }.getOrDefault(AppThemeMode.DARK)
        UserSettings(
            themeMode = theme,
            lastOpenedLessonId = prefs[Keys.LAST_OPENED_LESSON],
            dailyReminderEnabled = prefs[Keys.DAILY_REMINDER_ENABLED] ?: false,
            reminderHour = prefs[Keys.REMINDER_HOUR] ?: 20,
            reminderMinute = prefs[Keys.REMINDER_MINUTE] ?: 0,
            importedPdfUri = prefs[Keys.IMPORTED_PDF_URI],
            importedPdfName = prefs[Keys.IMPORTED_PDF_NAME],
            importedPdfPageCount = prefs[Keys.IMPORTED_PDF_PAGE_COUNT] ?: 0,
            lastPdfPageIndex = prefs[Keys.LAST_PDF_PAGE_INDEX] ?: 0
        )
    }

    suspend fun setThemeMode(mode: AppThemeMode) {
        context.dataStore.edit { it[Keys.THEME_MODE] = mode.name }
    }

    suspend fun setLastOpenedLessonId(lessonId: String) {
        context.dataStore.edit { it[Keys.LAST_OPENED_LESSON] = lessonId }
    }

    suspend fun setDailyReminder(enabled: Boolean, hour: Int, minute: Int) {
        context.dataStore.edit {
            it[Keys.DAILY_REMINDER_ENABLED] = enabled
            it[Keys.REMINDER_HOUR] = hour
            it[Keys.REMINDER_MINUTE] = minute
        }
    }

    suspend fun setImportedPdf(uri: String, name: String, pageCount: Int) {
        context.dataStore.edit {
            it[Keys.IMPORTED_PDF_URI] = uri
            it[Keys.IMPORTED_PDF_NAME] = name
            it[Keys.IMPORTED_PDF_PAGE_COUNT] = pageCount
            it[Keys.LAST_PDF_PAGE_INDEX] = 0
        }
    }

    suspend fun setLastPdfPageIndex(pageIndex: Int) {
        context.dataStore.edit { it[Keys.LAST_PDF_PAGE_INDEX] = pageIndex }
    }
}
