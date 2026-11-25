# কিছুই পারিনা – English Learning App

Native Android English-learning application (Bengali → English) built with **Kotlin**, **Jetpack Compose**, **Material 3**, **MVVM + Repository Pattern**, **Room Database**, **DataStore Preferences**, and **Navigation Compose**.

## Project & Feature Summary
- **100% Offline Architecture:** All lessons, A–Z vocabulary, verbs, patterns, dialogues, stories, quizzes, bookmarks, and learning progress are stored locally via Room and DataStore.
- **18+ Structured Learning Topics:** Alphabets A–Z, Capital & Small Letters, Pronunciation, Vocabulary A–Z, Basic Verbs A–Z, Sentence Patterns, I am / I want / I have, I am + Verb-ing, Present/Past/Future, Yes/No Questions, WH Questions, Advanced Questions, Dialogues, Prepositions, Adjectives, Sentence Making, Story Reading, and নিজের পরিচয় দেওয়া.
- **Interactive Practice Quizzes:** Category-wise and full-course MCQs with instant Bengali explanations and saved score history.
- **Offline Course PDF Reader & Verification Report:** Open `Kichui-Pari-Na-Course-Complete-FREE-PDF_copy.pdf` directly from device storage using Android's native `PdfRenderer` with page jump and page bookmarks.
- **Navy Dark & Crisp Light Modes:** Custom typography (`Plus Jakarta Sans` + `Noto Sans Bengali`) and daily learning reminder notifications.

---

## Building the Release APK (`.apk`, NOT `.aab`)

`app/build.gradle.kts` is pre-configured to sign release builds using environment variables (never hardcode passwords in source code):
- `KEYSTORE_PATH` (defaults to `my-upload-key.jks` in the project root)
- `STORE_PASSWORD`
- `KEY_PASSWORD`
- `keyAlias`: `upload`

### Step 1: Create a Secure Release Keystore (one-time)
Run in your local terminal (outside source control):
```bash
keytool -genkey -v -keystore my-upload-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias upload
```
Make sure `*.jks` / `*.keystore` is never committed to git.

### Step 2: Export Signing Environment Variables & Build Release APK
On macOS/Linux:
```bash
export KEYSTORE_PATH="$(pwd)/my-upload-key.jks"
export STORE_PASSWORD="your-store-password"
export KEY_PASSWORD="your-key-password"
./gradlew assembleRelease
```
On Windows PowerShell:
```powershell
$env:KEYSTORE_PATH="$PWD\my-upload-key.jks"
$env:STORE_PASSWORD="your-store-password"
$env:KEY_PASSWORD="your-key-password"
.\gradlew.bat assembleRelease
```

### Step 3: Output APK Location
- **Release APK (installable on Android phones):**
  `app/build/outputs/apk/release/app-release.apk`
- **Debug APK (already built for testing):**
  `app/build/outputs/apk/debug/app-debug.apk`

---

## Installing the APK on Your Android Phone
- **Via File Transfer / USB Copy:**
  1. Connect your phone via USB (File Transfer / MTP) or copy `app-release.apk` (or `app-debug.apk` for local testing) to your phone's `Downloads` folder.
  2. Open your phone's **Files** app and tap the `.apk` file.
  3. Allow **Install unknown apps** when prompted, then tap **Install**.
- **Via USB Debugging (`adb`):**
  1. Enable **Developer options → USB debugging** on your Android phone.
  2. Connect via USB and run:
     ```bash
     adb install -r app/build/outputs/apk/release/app-release.apk
     ```
