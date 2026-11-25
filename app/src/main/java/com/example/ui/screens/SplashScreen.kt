package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.ui.theme.LocalLearningPalette
import com.example.ui.theme.NavyDeep
import com.example.ui.theme.NavyPrimary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    isDataReady: Boolean,
    onFinished: () -> Unit
) {
    val scale = remember { Animatable(0.72f) }
    val palette = LocalLearningPalette.current

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    LaunchedEffect(isDataReady) {
        if (isDataReady) {
            delay(650)
            onFinished()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(NavyDeep, NavyPrimary)
                )
            )
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_app_logo),
                contentDescription = "কিছুই পারিনা অ্যাপ লোগো",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(132.dp)
                    .scale(scale.value)
                    .clip(RoundedCornerShape(32.dp))
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "কিছুই পারিনা",
                style = MaterialTheme.typography.displayLarge,
                color = palette.gold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "English Learning App",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "শূন্য থেকে সহজ বাংলায় ইংরেজি শেখার সম্পূর্ণ অফলাইন কোর্স",
                style = MaterialTheme.typography.bodyLarge,
                color = palette.sky,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(36.dp))

            CircularProgressIndicator(
                color = palette.gold,
                trackColor = NavyPrimary,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
