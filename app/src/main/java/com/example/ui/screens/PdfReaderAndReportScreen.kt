package com.example.ui.screens

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color as AndroidColor
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.FileOpen
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LocalLearningPalette
import com.example.ui.viewmodel.AppDashboardState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdfReaderAndReportScreen(
    state: AppDashboardState,
    onBack: () -> Unit,
    onPdfSelected: (Uri) -> Unit,
    onPageChanged: (Int) -> Unit,
    onTogglePageBookmark: (Int, Boolean) -> Unit
) {
    val context = LocalContext.current
    val palette = LocalLearningPalette.current
    val settings = state.settings

    val pdfPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            runCatching {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
            onPdfSelected(uri)
        }
    }

    var currentPageIndex by remember(settings.importedPdfUri) {
        mutableIntStateOf(settings.lastPdfPageIndex.coerceAtLeast(0))
    }
    var pageInput by remember { mutableStateOf("") }
    var renderedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var renderError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(settings.importedPdfUri, currentPageIndex) {
        val uriString = settings.importedPdfUri
        if (uriString == null) {
            renderedBitmap = null
            renderError = null
            return@LaunchedEffect
        }
        onPageChanged(currentPageIndex)
        val result = withContext(Dispatchers.IO) {
            runCatching {
                val uri = Uri.parse(uriString)
                val pfd = context.contentResolver.openFileDescriptor(uri, "r")
                    ?: error("PDF ফাইলটি খোলা যায়নি")
                pfd.use { descriptor ->
                    PdfRenderer(descriptor).use { renderer ->
                        val safeIdx = currentPageIndex.coerceIn(0, (renderer.pageCount - 1).coerceAtLeast(0))
                        renderer.openPage(safeIdx).use { page ->
                            val targetW = 1080
                            val targetH = ((targetW.toFloat() / page.width.coerceAtLeast(1)) * page.height).toInt().coerceAtLeast(1)
                            val bmp = Bitmap.createBitmap(targetW, targetH, Bitmap.Config.ARGB_8888)
                            bmp.eraseColor(AndroidColor.WHITE)
                            page.render(bmp, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                            bmp
                        }
                    }
                }
            }
        }
        result.onSuccess { bmp ->
            renderedBitmap = bmp
            renderError = null
        }.onFailure { err ->
            renderedBitmap = null
            renderError = err.message ?: "পৃষ্ঠা প্রদর্শন করা যায়নি"
        }
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
                        modifier = Modifier.minimumInteractiveComponentSize()
                    ) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "পিছনে যান")
                    }
                },
                title = {
                    Column {
                        Text("Course PDF & Verification", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "অফলাইন PDF রিডার এবং কনটেন্ট রিপোর্ট",
                            style = MaterialTheme.typography.labelSmall,
                            color = palette.sky
                        )
                    }
                }
            )
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
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Content Verification Report Card
                item {
                    val hasLocalPdf = settings.importedPdfUri != null && settings.importedPdfPageCount > 0
                    Card(
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(
                            1.dp,
                            if (hasLocalPdf) palette.success.copy(alpha = 0.6f)
                            else palette.gold.copy(alpha = 0.6f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (hasLocalPdf) Icons.Rounded.Verified else Icons.Rounded.WarningAmber,
                                    contentDescription = null,
                                    tint = if (hasLocalPdf) palette.success else palette.gold
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "PDF Content Verification Report",
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }

                            Text(
                                text = "• নির্ধারিত PDF: Kichui-Pari-Na-Course-Complete-FREE-PDF_copy.pdf (৩২৩ পৃষ্ঠা)\n" +
                                    "• বিল্ডের সময় প্রসেসকৃত PDF পৃষ্ঠা: ০ / ৩২৩ (PDF ফাইলটি বিল্ড ওয়ার্কস্পেসে সংযুক্ত ছিল না)\n" +
                                    "• সমস্যাযুক্ত/অনুপলব্ধ পৃষ্ঠা: পৃষ্ঠা ১ থেকে ৩২৩\n" +
                                    "• অ্যাপে অন্তর্ভুক্ত অনুশীলন পাঠ: ${state.totalCount}টি সম্পূর্ণ বাংলা-ইংরেজি পাঠ ও ১৮টি কুইজ (স্বচ্ছভাবে অনুশীলন কারিকুলাম হিসেবে চিহ্নিত)",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            if (hasLocalPdf) {
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = palette.successContainer
                                ) {
                                    Text(
                                        text = "ডিভাইস থেকে লোডকৃত PDF: ${settings.importedPdfName} (${settings.importedPdfPageCount} পৃষ্ঠা)",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = palette.success,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                    )
                                }
                            }

                            Button(
                                onClick = { pdfPickerLauncher.launch(arrayOf("application/pdf")) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .minimumInteractiveComponentSize()
                                    .testTag("open_pdf_picker_button"),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = palette.gold,
                                    contentColor = Color(0xFF081229)
                                )
                            ) {
                                Icon(Icons.Rounded.FileOpen, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (hasLocalPdf) "অন্য PDF ফাইল নির্বাচন করুন" else "ফোন থেকে কোর্স PDF খুলুন (অফলাইন)",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // Native PdfRenderer Viewer if user picked a PDF
                if (settings.importedPdfUri != null && settings.importedPdfPageCount > 0) {
                    val totalPages = settings.importedPdfPageCount
                    val isBookmarked = state.pdfBookmarks.any { it.pageIndex == currentPageIndex }

                    item {
                        Card(
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "পৃষ্ঠা ${currentPageIndex + 1} / $totalPages",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = palette.gold
                                    )
                                    IconButton(
                                        onClick = { onTogglePageBookmark(currentPageIndex, isBookmarked) },
                                        modifier = Modifier.minimumInteractiveComponentSize()
                                    ) {
                                        Icon(
                                            imageVector = if (isBookmarked) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
                                            contentDescription = "পৃষ্ঠা বুকমার্ক করুন",
                                            tint = if (isBookmarked) palette.gold else MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }

                                if (state.pdfBookmarks.isNotEmpty()) {
                                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                        items(state.pdfBookmarks, key = { it.pageIndex }) { bm ->
                                            FilterChip(
                                                selected = bm.pageIndex == currentPageIndex,
                                                onClick = { currentPageIndex = bm.pageIndex },
                                                label = { Text("পৃষ্ঠা ${bm.pageIndex + 1}") }
                                            )
                                        }
                                    }
                                }

                                val bmp = renderedBitmap
                                if (bmp != null) {
                                    val aspect = bmp.width.toFloat() / bmp.height.coerceAtLeast(1).toFloat()
                                    Image(
                                        bitmap = bmp.asImageBitmap(),
                                        contentDescription = "PDF পৃষ্ঠা ${currentPageIndex + 1}",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(aspect)
                                            .background(Color.White, RoundedCornerShape(12.dp))
                                    )
                                } else if (renderError != null) {
                                    Text(
                                        text = renderError!!,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    OutlinedButton(
                                        onClick = { if (currentPageIndex > 0) currentPageIndex-- },
                                        enabled = currentPageIndex > 0,
                                        modifier = Modifier
                                            .weight(1f)
                                            .minimumInteractiveComponentSize()
                                    ) {
                                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("আগের পৃষ্ঠা")
                                    }
                                    OutlinedButton(
                                        onClick = { if (currentPageIndex + 1 < totalPages) currentPageIndex++ },
                                        enabled = currentPageIndex + 1 < totalPages,
                                        modifier = Modifier
                                            .weight(1f)
                                            .minimumInteractiveComponentSize()
                                    ) {
                                        Text("পরের পৃষ্ঠা")
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Icon(Icons.AutoMirrored.Rounded.ArrowForward, contentDescription = null)
                                    }
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    OutlinedTextField(
                                        value = pageInput,
                                        onValueChange = { pageInput = it.filter { ch -> ch.isDigit() } },
                                        modifier = Modifier.weight(1f),
                                        label = { Text("পৃষ্ঠা নম্বর (১-$totalPages)") },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        singleLine = true
                                    )
                                    Button(
                                        onClick = {
                                            val target = pageInput.toIntOrNull()
                                            if (target != null && target in 1..totalPages) {
                                                currentPageIndex = target - 1
                                                pageInput = ""
                                            }
                                        },
                                        modifier = Modifier.minimumInteractiveComponentSize()
                                    ) {
                                        Text("যান")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
