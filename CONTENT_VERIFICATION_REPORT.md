# Content Verification Report — কিছুই পারিনা – English Learning App

## 1. PDF Extraction Summary
- **Target PDF File:** `Kichui-Pari-Na-Course-Complete-FREE-PDF_copy.pdf` (Expected: 323 pages)
- **Total PDF Pages Processed at Build Time:** `0 / 323`
- **Total Lessons Extracted Directly from PDF:** `0`
- **Pages with Extraction Problems:** **Pages 1–323** — The PDF file `Kichui-Pari-Na-Course-Complete-FREE-PDF_copy.pdf` was not attached in the prompt or present in the build workspace (`/app/applet`). Per the zero-fabrication rule, no invented content is labeled as PDF content.

## 2. Included Offline Starter Curriculum (Explicitly Separated from PDF Content)
All 18+ requested learning topics are implemented as an offline Room-backed **Starter Practice Curriculum** (19 interactive lessons + 18 practice quiz questions), clearly labeled inside the app as practice curriculum rather than extracted PDF pages:

1. `lesson_alphabets` — English Alphabets (A–Z)
2. `lesson_capital_small` — Capital Letters & Small Letters
3. `lesson_pronunciation` — Alphabet Pronunciation (A–Z in Bengali)
4. `lesson_vocab_am` — English Vocabulary (A–M)
5. `lesson_vocab_nz` — English Vocabulary (N–Z)
6. `lesson_verbs_az` — Basic English Verbs (A–Z, V1/V2/V3)
7. `lesson_sentence_patterns` — English Sentence Patterns
8. `lesson_i_am_want_have` — I am / I want / I have
9. `lesson_i_am_verbing` — I am + Verb-ing
10. `lesson_tenses` — Present, Past & Future Patterns
11. `lesson_prepositions` — English Prepositions
12. `lesson_adjectives` — English Adjectives
13. `lesson_sentence_making` — Sentence Making
14. `lesson_english_questions` — English Questions (Yes/No)
15. `lesson_wh_questions` — WH Questions
16. `lesson_advanced_questions` — Advanced Questions
17. `lesson_dialogues` — English Dialogues
18. `lesson_story_reading` — Story Reading
19. `lesson_self_intro` — নিজের পরিচয় দেওয়া (Self Introduction)

## 3. On-Device Offline PDF Support
Users can select `Kichui-Pari-Na-Course-Complete-FREE-PDF_copy.pdf` directly from their Android phone in **Course PDF & Verification** inside the app. The app uses Android's native `PdfRenderer` to display all pages completely offline with page jump and page bookmarks.
