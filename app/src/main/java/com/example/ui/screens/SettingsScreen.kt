package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NotificationsActive
import androidx.compose.material.icons.rounded.PictureAsPdf
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.ui.components.SectionHeader
import com.example.ui.theme.AppThemeMode
import com.example.ui.theme.LocalLearningPalette
import com.example.ui.viewmodel.AppDashboardState
import java.util.Locale

@Composable
fun SettingsScreen(
    state: AppDashboardState,
    onThemeChange: (AppThemeMode) -> Unit,
    onReminderToggle: (Boolean, Int, Int) -> Unit,
    onTestNotification: () -> Unit,
    onOpenPdfAndReport: () -> Unit
) {
    val context = LocalContext.current
    val palette = LocalLearningPalette.current
    val settings = state.settings
    var permissionDeniedNote by remember { mutableStateOf(false) }

    val notificationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            permissionDeniedNote = false
            onReminderToggle(true, settings.reminderHour, settings.reminderMinute)
        } else {
            permissionDeniedNote = true
            onReminderToggle(false, settings.reminderHour, settings.reminderMinute)
        }
    }

    fun requestOrEnableReminder(enable: Boolean, hour: Int, minute: Int) {
        if (!enable) {
            onReminderToggle(false, hour, minute)
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
            if (granted) {
                onReminderToggle(true, hour, minute)
            } else {
                notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            onReminderToggle(true, hour, minute)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 760.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                SectionHeader(
                    titleEn = "Settings",
                    titleBn = "অ্যাপের থিম, দৈনিক রিমাইন্ডার ও নিরাপত্তা"
                )
            }

            // Theme selector
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.45f))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("থিম নির্বাচন করুন (Theme)", style = MaterialTheme.typography.titleMedium)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(
                                AppThemeMode.DARK to "Navy Dark",
                                AppThemeMode.LIGHT to "Light Mode",
                                AppThemeMode.SYSTEM to "System"
                            ).forEach { (mode, label) ->
                                FilterChip(
                                    selected = settings.themeMode == mode,
                                    onClick = { onThemeChange(mode) },
                                    label = { Text(label) },
                                    modifier = Modifier.testTag("theme_chip_${mode.name.lowercase()}")
                                )
                            }
                        }
                    }
                }
            }

            // Daily learning reminder
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.45f))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    Icons.Rounded.NotificationsActive,
                                    contentDescription = null,
                                    tint = palette.gold
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        "দৈনিক শেখার রিমাইন্ডার",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        String.format(
                                            Locale.getDefault(),
                                            "প্রতিদিন %02d:%02d সময়ে নোটিফিকেশন",
                                            settings.reminderHour,
                                            settings.reminderMinute
                                        ),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            Switch(
                                checked = settings.dailyReminderEnabled,
                                onCheckedChange = { checked ->
                                    requestOrEnableReminder(checked, settings.reminderHour, settings.reminderMinute)
                                },
                                modifier = Modifier.testTag("daily_reminder_switch")
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(
                                8 to "সকাল ৮টা",
                                17 to "বিকাল ৫টা",
                                20 to "রাত ৮টা",
                                21 to "রাত ৯টা"
                            ).forEach { (hour, label) ->
                                FilterChip(
                                    selected = settings.reminderHour == hour,
                                    onClick = {
                                        requestOrEnableReminder(true, hour, 0)
                                    },
                                    label = { Text(label) }
                                )
                            }
                        }

                        if (permissionDeniedNote) {
                            Text(
                                text = "রিমাইন্ডার চালু করতে ফোনের নোটিফিকেশন অনুমতি প্রয়োজন।",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        OutlinedButton(
                            onClick = {
                                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
                                    ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
                                ) {
                                    onTestNotification()
                                } else {
                                    notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                }
                            },
                            modifier = Modifier.minimumInteractiveComponentSize()
                        ) {
                            Text("টেস্ট নোটিফিকেশন পাঠান")
                        }
                    }
                }
            }

            // PDF & Content Verification Report Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, palette.sky.copy(alpha = 0.45f))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Rounded.PictureAsPdf, contentDescription = null, tint = palette.sky)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                "Course PDF ও যাচাই প্রতিবেদন",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        Text(
                            "৩২৩ পৃষ্ঠার কোর্স PDF অফলাইনে খুলুন এবং বিস্তারিত Content Verification Report দেখুন।",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Button(
                            onClick = onOpenPdfAndReport,
                            modifier = Modifier
                                .fillMaxWidth()
                                .minimumInteractiveComponentSize()
                                .testTag("settings_open_pdf_report_button")
                        ) {
                            Text("PDF রিডার ও রিপোর্ট খুলুন")
                        }
                    }
                }
            }

            // Offline & Privacy Security Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, palette.success.copy(alpha = 0.45f))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            Icons.Rounded.Security,
                            contentDescription = null,
                            tint = palette.success
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                "১০০% অফলাইন ও নিরাপদ",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                "আপনার সম্পন্ন পাঠ, বুকমার্ক ও কুইজের স্কোর শুধুমাত্র আপনার ডিভাইসের লোকাল Room ডাটাবেসে সংরক্ষিত থাকে। ইন্টারনেট বা কোনো ব্যক্তিগত তথ্যের প্রয়োজন নেই।",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
