package com.example.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Quiz
import androidx.compose.material.icons.rounded.Spellcheck
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.AppContainer
import com.example.ui.navigation.Routes
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LessonDetailScreen
import com.example.ui.screens.LessonsListScreen
import com.example.ui.screens.PdfReaderAndReportScreen
import com.example.ui.screens.ProgressAndBookmarksScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.screens.VocabularyScreen
import com.example.ui.viewmodel.LessonDetailViewModel
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.QuizViewModel

private data class BottomNavTab(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val testTag: String
)

@Composable
fun KichuiParinaAppRoot(
    container: AppContainer,
    mainViewModel: MainViewModel
) {
    val navController = rememberNavController()
    val dashboardState by mainViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val tabs = listOf(
        BottomNavTab(Routes.HOME, "Home", Icons.Rounded.Home, "nav_home"),
        BottomNavTab(Routes.LESSONS, "Lessons", Icons.AutoMirrored.Rounded.MenuBook, "nav_lessons"),
        BottomNavTab(Routes.VOCABULARY, "Vocab", Icons.Rounded.Spellcheck, "nav_vocab"),
        BottomNavTab(Routes.QUIZ, "Quiz", Icons.Rounded.Quiz, "nav_quiz"),
        BottomNavTab(Routes.PROGRESS, "Progress", Icons.Rounded.TrendingUp, "nav_progress")
    )

    val showChrome = currentRoute != null &&
        currentRoute != Routes.SPLASH &&
        !currentRoute.startsWith("lesson_detail") &&
        currentRoute != Routes.PDF_READER

    fun navigateTopTab(route: String) {
        navController.navigate(route) {
            popUpTo(Routes.HOME) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useRail = maxWidth >= 600.dp

        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = {
                if (showChrome && !useRail) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {
                        tabs.forEach { tab ->
                            val selected = currentRoute == tab.route ||
                                (tab.route == Routes.QUIZ && currentRoute?.startsWith("quiz") == true)
                            NavigationBarItem(
                                selected = selected,
                                onClick = { navigateTopTab(tab.route) },
                                icon = { Icon(tab.icon, contentDescription = tab.label) },
                                label = { Text(tab.label) },
                                modifier = Modifier.testTag(tab.testTag)
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (showChrome && useRail) {
                    NavigationRail(
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {
                        tabs.forEach { tab ->
                            val selected = currentRoute == tab.route ||
                                (tab.route == Routes.QUIZ && currentRoute?.startsWith("quiz") == true)
                            NavigationRailItem(
                                selected = selected,
                                onClick = { navigateTopTab(tab.route) },
                                icon = { Icon(tab.icon, contentDescription = tab.label) },
                                label = { Text(tab.label) },
                                modifier = Modifier.testTag(tab.testTag)
                            )
                        }
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = Routes.SPLASH,
                    modifier = Modifier.weight(1f)
                ) {
                    composable(Routes.SPLASH) {
                        SplashScreen(
                            isDataReady = dashboardState.isReady,
                            onFinished = {
                                navController.navigate(Routes.HOME) {
                                    popUpTo(Routes.SPLASH) { inclusive = true }
                                }
                            }
                        )
                    }

                    composable(Routes.HOME) {
                        HomeScreen(
                            state = dashboardState,
                            onOpenLesson = { lessonId ->
                                navController.navigate(Routes.lessonDetail(lessonId))
                            },
                            onOpenAllLessons = { navigateTopTab(Routes.LESSONS) },
                            onOpenVocabulary = { navigateTopTab(Routes.VOCABULARY) },
                            onOpenQuiz = { categoryId ->
                                if (categoryId == "all") {
                                    navigateTopTab(Routes.QUIZ)
                                } else {
                                    navController.navigate(Routes.quizWithCategory(categoryId))
                                }
                            },
                            onOpenProgress = { navigateTopTab(Routes.PROGRESS) },
                            onOpenPdfReader = { navController.navigate(Routes.PDF_READER) },
                            onOpenSettings = { navController.navigate(Routes.SETTINGS) },
                            onToggleBookmark = { mainViewModel.toggleLessonBookmark(it) }
                        )
                    }

                    composable(Routes.LESSONS) {
                        LessonsListScreen(
                            state = dashboardState,
                            onSearchChange = mainViewModel::updateSearchQuery,
                            onFilterChange = mainViewModel::updateFilter,
                            onOpenLesson = { lessonId ->
                                navController.navigate(Routes.lessonDetail(lessonId))
                            },
                            onToggleBookmark = mainViewModel::toggleLessonBookmark
                        )
                    }

                    composable(Routes.VOCABULARY) {
                        VocabularyScreen(state = dashboardState)
                    }

                    composable(Routes.QUIZ) {
                        val quizVm: QuizViewModel = viewModel(
                            factory = QuizViewModel.Factory(container.learningRepository, "all")
                        )
                        val quizState by quizVm.uiState.collectAsStateWithLifecycle()
                        QuizScreen(
                            uiState = quizState,
                            onSelectCategory = quizVm::loadQuiz,
                            onSelectOption = quizVm::selectOption,
                            onSubmitAnswer = quizVm::submitAnswer,
                            onNextQuestion = quizVm::nextQuestion,
                            onRestartQuiz = quizVm::restartQuiz
                        )
                    }

                    composable(
                        route = Routes.QUIZ_ROUTE_PATTERN,
                        arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val catId = backStackEntry.arguments?.getString("categoryId") ?: "all"
                        val quizVm: QuizViewModel = viewModel(
                            key = "quiz_$catId",
                            factory = QuizViewModel.Factory(container.learningRepository, catId)
                        )
                        val quizState by quizVm.uiState.collectAsStateWithLifecycle()
                        QuizScreen(
                            uiState = quizState,
                            onSelectCategory = quizVm::loadQuiz,
                            onSelectOption = quizVm::selectOption,
                            onSubmitAnswer = quizVm::submitAnswer,
                            onNextQuestion = quizVm::nextQuestion,
                            onRestartQuiz = quizVm::restartQuiz
                        )
                    }

                    composable(Routes.PROGRESS) {
                        ProgressAndBookmarksScreen(
                            state = dashboardState,
                            onOpenLesson = { lessonId ->
                                navController.navigate(Routes.lessonDetail(lessonId))
                            },
                            onToggleBookmark = mainViewModel::toggleLessonBookmark
                        )
                    }

                    composable(Routes.SETTINGS) {
                        SettingsScreen(
                            state = dashboardState,
                            onThemeChange = mainViewModel::setThemeMode,
                            onReminderToggle = { enabled, h, m ->
                                mainViewModel.updateDailyReminder(context, enabled, h, m)
                            },
                            onTestNotification = { mainViewModel.sendTestNotification(context) },
                            onOpenPdfAndReport = { navController.navigate(Routes.PDF_READER) }
                        )
                    }

                    composable(Routes.PDF_READER) {
                        PdfReaderAndReportScreen(
                            state = dashboardState,
                            onBack = { navController.popBackStack() },
                            onPdfSelected = { uri -> mainViewModel.importPdfFromUri(context, uri) },
                            onPageChanged = mainViewModel::updateLastPdfPage,
                            onTogglePageBookmark = mainViewModel::togglePdfBookmark
                        )
                    }

                    composable(
                        route = Routes.LESSON_DETAIL,
                        arguments = listOf(navArgument("lessonId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val lessonId = backStackEntry.arguments?.getString("lessonId") ?: "lesson_alphabets"
                        val detailVm: LessonDetailViewModel = viewModel(
                            key = "detail_$lessonId",
                            factory = LessonDetailViewModel.Factory(lessonId, container.learningRepository)
                        )
                        val detailState by detailVm.uiState.collectAsStateWithLifecycle()
                        LessonDetailScreen(
                            uiState = detailState,
                            onBack = { navController.popBackStack() },
                            onNavigateToLesson = { targetId ->
                                navController.navigate(Routes.lessonDetail(targetId)) {
                                    popUpTo(navController.currentBackStackEntry?.destination?.id ?: navController.graph.findStartDestination().id) {
                                        inclusive = true
                                    }
                                }
                            },
                            onToggleCompleted = detailVm::toggleCompleted,
                            onToggleBookmark = detailVm::toggleBookmark,
                            onStartCategoryQuiz = { categoryId ->
                                navController.navigate(Routes.quizWithCategory(categoryId))
                            }
                        )
                    }
                }
            }
        }
    }
}
