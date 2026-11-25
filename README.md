# কিছুই পারিনা – English Learning App

Native Android English-learning application (Bengali → English) built with **Kotlin**, **Jetpack Compose**, **Material 3**, **MVVM + Repository Pattern**, **Room Database**, **DataStore Preferences**, and **Navigation Compose**.

## Key Features
- **100% Offline Architecture:** All lessons, A–Z vocabulary, verbs, patterns, dialogues, stories, quizzes, bookmarks, and learning progress are stored locally via Room and DataStore.
- **18+ Structured Learning Topics:** Alphabets A–Z, Capital & Small Letters, Pronunciation, Vocabulary A–Z, Basic Verbs A–Z, Sentence Patterns, I am / I want / I have, I am + Verb-ing, Present/Past/Future, Yes/No Questions, WH Questions, Advanced Questions, Dialogues, Prepositions, Adjectives, Sentence Making, Story Reading, and নিজের পরিচয় দেওয়া.
- **Interactive Practice Quizzes:** Category-wise and full-course MCQs with instant Bengali explanations and saved score history.
- **Offline Course PDF Reader & Verification Report:** Open `Kichui-Pari-Na-Course-Complete-FREE-PDF_copy.pdf` directly from device storage using Android's native `PdfRenderer` with page jump and page bookmarks.
- **Navy Dark & Crisp Light Modes:** Custom typography (`Plus Jakarta Sans` + `Noto Sans Bengali`) and daily learning reminder notifications.

## How to Build `app-debug.apk`
1. Open this project in **Android Studio** (or use Gradle CLI with Android SDK configured).
2. Sync Gradle dependencies.
3. Run:
   ```bash
   gradle assembleDebug
   ```
   (Or `./gradlew assembleDebug` in your local Android Studio terminal.)
4. The installable APK is generated at:
   `app/build/outputs/apk/debug/app-debug.apk`
