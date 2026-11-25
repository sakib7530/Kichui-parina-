package com.example.data.seed

import com.example.data.local.entity.LessonCardEntity
import com.example.data.local.entity.LessonEntity

object AlphabetAndVocabSeed {

    const val SOURCE_STARTER = "STARTER_CURRICULUM"
    const val PDF_NOTE_UNAVAILABLE = "অফলাইন অনুশীলন পাঠ (মূল ৩২৩ পৃষ্ঠার PDF এখনো যুক্ত হয়নি)"

    fun lessons(): List<LessonEntity> = listOf(
        LessonEntity(
            id = "lesson_alphabets",
            categoryId = "alphabets",
            categoryTitleEn = "English Alphabets",
            categoryTitleBn = "ইংরেজি বর্ণমালা (A–Z)",
            orderIndex = 1,
            titleEn = "English Alphabets A to Z",
            titleBn = "ইংরেজি ২৬টি বর্ণ পরিচিতি",
            bengaliExplanation = "ইংরেজি ভাষায় মোট ২৬টি বর্ণ (Alphabet) রয়েছে। এর মধ্যে ৫টি Vowel (স্বরবর্ণ: A, E, I, O, U) এবং ২১টি Consonant (ব্যঞ্জনবর্ণ)। প্রতিটি বর্ণের সাথে একটি সহজ শব্দ ও বাংলা অর্থ দেওয়া হলো।",
            contentSource = SOURCE_STARTER,
            pdfPageNote = PDF_NOTE_UNAVAILABLE,
            iconKey = "abc"
        ),
        LessonEntity(
            id = "lesson_capital_small",
            categoryId = "capital_small",
            categoryTitleEn = "Capital & Small Letters",
            categoryTitleBn = "বড় হাতের ও ছোট হাতের অক্ষর",
            orderIndex = 2,
            titleEn = "Capital & Small Letters Guide",
            titleBn = "Capital ও Small Letter চেনা ও ব্যবহার",
            bengaliExplanation = "ইংরেজিতে প্রতিটি বর্ণ দুইভাবে লেখা হয়: Capital Letter (বড় হাতের অক্ষর) এবং Small Letter (ছোট হাতের অক্ষর)। বাক্যের প্রথম অক্ষর, মানুষের নাম, দেশের নাম, দিন ও মাসের নামের শুরুতে Capital Letter বসে এবং 'I' (আমি) সবসময় বড় হাতের হয়।",
            contentSource = SOURCE_STARTER,
            pdfPageNote = PDF_NOTE_UNAVAILABLE,
            iconKey = "format"
        ),
        LessonEntity(
            id = "lesson_pronunciation",
            categoryId = "pronunciation",
            categoryTitleEn = "Alphabet Pronunciation",
            categoryTitleBn = "বর্ণের সঠিক উচ্চারণ",
            orderIndex = 3,
            titleEn = "A–Z Pronunciation in Bengali",
            titleBn = "প্রতিটি ইংরেজি অক্ষরের বাংলা উচ্চারণ",
            bengaliExplanation = "সঠিকভাবে ইংরেজি পড়তে ও বলতে গেলে প্রতিটি বর্ণের নিজস্ব নাম ও শব্দে তার ধ্বনি জানা জরুরি। নিচে ২৬টি বর্ণের সহজ বাংলা উচ্চারণ এবং প্রয়োজনীয় ধ্বনি-নিয়ম দেওয়া হলো।",
            contentSource = SOURCE_STARTER,
            pdfPageNote = PDF_NOTE_UNAVAILABLE,
            iconKey = "voice"
        ),
        LessonEntity(
            id = "lesson_vocab_am",
            categoryId = "vocabulary_az",
            categoryTitleEn = "English Vocabulary A–Z",
            categoryTitleBn = "প্রয়োজনীয় শব্দভাণ্ডার (A–Z)",
            orderIndex = 4,
            titleEn = "Essential Vocabulary: A to M",
            titleBn = "দৈনন্দিন ইংরেজি শব্দভাণ্ডার (A থেকে M)",
            bengaliExplanation = "ইংরেজি বলার জন্য প্রতিদিন ব্যবহৃত সহজ শব্দগুলো জানা খুব দরকার। এখানে A থেকে M পর্যন্ত গুরুত্বপূর্ণ শব্দ, বাংলা উচ্চারণ ও বাক্যে ব্যবহার দেওয়া হলো।",
            contentSource = SOURCE_STARTER,
            pdfPageNote = PDF_NOTE_UNAVAILABLE,
            iconKey = "vocab"
        ),
        LessonEntity(
            id = "lesson_vocab_nz",
            categoryId = "vocabulary_az",
            categoryTitleEn = "English Vocabulary A–Z",
            categoryTitleBn = "প্রয়োজনীয় শব্দভাণ্ডার (A–Z)",
            orderIndex = 5,
            titleEn = "Essential Vocabulary: N to Z",
            titleBn = "দৈনন্দিন ইংরেজি শব্দভাণ্ডার (N থেকে Z)",
            bengaliExplanation = "N থেকে Z পর্যন্ত দৈনন্দিন জীবনে ব্যবহৃত ইংরেজি শব্দ, বাংলা উচ্চারণ, অর্থ এবং উদাহরণ বাক্য নিচে দেওয়া হলো।",
            contentSource = SOURCE_STARTER,
            pdfPageNote = PDF_NOTE_UNAVAILABLE,
            iconKey = "vocab"
        ),
        LessonEntity(
            id = "lesson_verbs_az",
            categoryId = "verbs_az",
            categoryTitleEn = "Basic English Verbs A–Z",
            categoryTitleBn = "বেসিক ক্রিয়াপদ (Verbs A–Z)",
            orderIndex = 6,
            titleEn = "Basic Action Verbs A to Z",
            titleBn = "কাজ বোঝায় এমন ২৬টি জরুরি Verb",
            bengaliExplanation = "যে শব্দ দিয়ে কোনো কাজ করা বোঝায় তাকে Verb বলে। প্রতিটি Verb-এর তিনটি রূপ (V1 Present, V2 Past, V3 Past Participle) ও বাংলা অর্থ মনে রাখলে যেকোনো সময়ের বাক্য তৈরি সহজ হয়।",
            contentSource = SOURCE_STARTER,
            pdfPageNote = PDF_NOTE_UNAVAILABLE,
            iconKey = "verb"
        )
    )

    fun cards(): List<LessonCardEntity> {
        val list = mutableListOf<LessonCardEntity>()

        // 1. Alphabets A-Z
        val alphabetData = listOf(
            Triple("A a", "Vowel (স্বরবর্ণ)", "Apple — আপেল"),
            Triple("B b", "Consonant (ব্যঞ্জনবর্ণ)", "Book — বই"),
            Triple("C c", "Consonant (ব্যঞ্জনবর্ণ)", "Cat — বিড়াল"),
            Triple("D d", "Consonant (ব্যঞ্জনবর্ণ)", "Door — দরজা"),
            Triple("E e", "Vowel (স্বরবর্ণ)", "Egg — ডিম"),
            Triple("F f", "Consonant (ব্যঞ্জনবর্ণ)", "Fish — মাছ"),
            Triple("G g", "Consonant (ব্যঞ্জনবর্ণ)", "Girl — মেয়ে"),
            Triple("H h", "Consonant (ব্যঞ্জনবর্ণ)", "House — বাড়ি"),
            Triple("I i", "Vowel (স্বরবর্ণ)", "Ice — বরফ"),
            Triple("J j", "Consonant (ব্যঞ্জনবর্ণ)", "Juice — রস / জুস"),
            Triple("K k", "Consonant (ব্যঞ্জনবর্ণ)", "Key — চাবি"),
            Triple("L l", "Consonant (ব্যঞ্জনবর্ণ)", "Light — আলো"),
            Triple("M m", "Consonant (ব্যঞ্জনবর্ণ)", "Moon — চাঁদ"),
            Triple("N n", "Consonant (ব্যঞ্জনবর্ণ)", "Name — নাম"),
            Triple("O o", "Vowel (স্বরবর্ণ)", "Orange — কমলা"),
            Triple("P p", "Consonant (ব্যঞ্জনবর্ণ)", "Pen — কলম"),
            Triple("Q q", "Consonant (ব্যঞ্জনবর্ণ)", "Queen — রানী"),
            Triple("R r", "Consonant (ব্যঞ্জনবর্ণ)", "Rice — ভাত / চাল"),
            Triple("S s", "Consonant (ব্যঞ্জনবর্ণ)", "Sun — সূর্য"),
            Triple("T t", "Consonant (ব্যঞ্জনবর্ণ)", "Tree — গাছ"),
            Triple("U u", "Vowel (স্বরবর্ণ)", "Umbrella — ছাতা"),
            Triple("V v", "Consonant (ব্যঞ্জনবর্ণ)", "Village — গ্রাম"),
            Triple("W w", "Consonant (ব্যঞ্জনবর্ণ)", "Water — পানি"),
            Triple("X x", "Consonant (ব্যঞ্জনবর্ণ)", "X-ray — এক্স-রে"),
            Triple("Y y", "Semi-Vowel", "Yellow — হলুদ"),
            Triple("Z z", "Consonant (ব্যঞ্জনবর্ণ)", "Zoo — চিড়িয়াখানা")
        )
        alphabetData.forEachIndexed { idx, (letter, kind, wordBn) ->
            list.add(
                LessonCardEntity(
                    lessonId = "lesson_alphabets",
                    cardOrder = idx + 1,
                    cardType = "ALPHABET",
                    primaryText = letter,
                    secondaryText = kind,
                    bengaliMeaning = wordBn,
                    exampleEn = "${letter.first()} is for ${wordBn.substringBefore(" —")}",
                    exampleBn = "${letter.first()} দিয়ে ${wordBn.substringAfter("— ")}",
                    cardColorIndex = idx % 8
                )
            )
        }

        // 2. Capital & Small Letters
        val capRules = listOf(
            listOf("A — a", "বাক্যের শুরুতে Capital", "My name is Rahim.", "বাক্যের প্রথম অক্ষর M বড় হাতের হবে।"),
            listOf("B — b", "মানুষের নামের শুরুতে", "Karim lives in Dhaka.", "Karim ও Dhaka নাম হওয়ায় K ও D বড় হাতের।"),
            listOf("C — c", "'I' (আমি) সর্বদা Capital", "You and I are friends.", "বাক্যের মাঝখানেও 'I' সবসময় বড় হাতের বসে।"),
            listOf("D — d", "বার ও মাসের নাম", "Today is Friday in May.", "Friday ও May বড় হাতের অক্ষরে শুরু হয়।"),
            listOf("E–H | e–h", "বড় ও ছোট অক্ষরের জোড়া", "E-e, F-f, G-g, H-h", "ছাপার অক্ষরে ছোট ও বড় রূপ চিনে রাখা জরুরি।"),
            listOf("I–M | i–m", "বড় ও ছোট অক্ষরের জোড়া", "I-i, J-j, K-k, L-l, M-m", "শিরোনাম ও সাধারণ শব্দে Small Letter বসে।"),
            listOf("N–S | n–s", "বড় ও ছোট অক্ষরের জোড়া", "N-n, O-o, P-p, Q-q, R-r, S-s", "প্রতিদিন খাতায় লিখে অনুশীলন করুন।"),
            listOf("T–Z | t–z", "বড় ও ছোট অক্ষরের জোড়া", "T-t, U-u, V-v, W-w, X-x, Y-y, Z-z", "ইংরেজি ২৬টি বর্ণের Capital ও Small রূপ সম্পূর্ণ।")
        )
        capRules.forEachIndexed { idx, row ->
            list.add(
                LessonCardEntity(
                    lessonId = "lesson_capital_small",
                    cardOrder = idx + 1,
                    cardType = "EXPLANATION",
                    primaryText = row[0],
                    secondaryText = row[1],
                    bengaliMeaning = row[3],
                    exampleEn = row[2],
                    exampleBn = row[3],
                    cardColorIndex = idx % 8
                )
            )
        }

        // 3. Pronunciation A-Z
        val pronData = listOf(
            Triple("A (এই / অ্যা)", "স্বরবর্ণ ধ্বনি", "Name (নেইম), Cat (ক্যাট)"),
            Triple("B (বি)", "ব-ধ্বনি", "Ball (বল), Bag (ব্যাগ)"),
            Triple("C (সি — ক / স)", "e, i, y এর আগে 'স', নইলে 'ক'", "Cat (ক্যাট), City (সিটি)"),
            Triple("D (ডি)", "ড-ধ্বনি", "Day (ডে), Desk (ডেস্ক)"),
            Triple("E (ই / এ)", "স্বরবর্ণ ধ্বনি", "Pen (পেন), Me (মি)"),
            Triple("F (এফ)", "ফ-ধ্বনি (নিচের ঠোঁটে)", "Fan (ফ্যান), Food (ফুড)"),
            Triple("G (জি — গ / জ)", "e, i, y এর আগে 'জ', নইলে 'গ'", "Go (গো), Page (পেইজ)"),
            Triple("H (এইচ)", "হ-ধ্বনি", "Hat (হ্যাট), Home (হোম)"),
            Triple("I (আই / ই)", "স্বরবর্ণ ধ্বনি", "Sit (সিট), Time (টাইম)"),
            Triple("J (জে)", "জ-ধ্বনি", "Job (জব), Joy (জয়)"),
            Triple("K (কে)", "ক-ধ্বনি", "King (কিং), Kite (কাইট)"),
            Triple("L (এল)", "ল-ধ্বনি", "Love (লাভ), Lamp (ল্যাম্প)"),
            Triple("M (এম)", "ম-ধ্বনি", "Man (ম্যান), Milk (মিল্ক)"),
            Triple("N (এন)", "ন-ধ্বনি", "Net (নেট), Nine (নাইন)"),
            Triple("O (ও / অ)", "স্বরবর্ণ ধ্বনি", "Open (ওপেন), Hot (হট)"),
            Triple("P (পি)", "প-ধ্বনি", "Pen (পেন), Paper (পেপার)"),
            Triple("Q (কিউ)", "কো-ধ্বনি (সাথে u থাকে)", "Queen (কুইন), Quick (কুইক)"),
            Triple("R (আর)", "র-ধ্বনি", "Read (রিড), Red (রেড)"),
            Triple("S (এস)", "স-ধ্বনি", "Sun (সান), School (স্কুল)"),
            Triple("T (টি)", "ট-ধ্বনি", "Tea (টি), Table (টেবিল)"),
            Triple("U (ইউ / আ)", "স্বরবর্ণ ধ্বনি", "Cup (কাপ), Use (ইউজ)"),
            Triple("V (ভি)", "ভ-ধ্বনি (দাঁত ও ঠোঁটে)", "Van (ভ্যান), Very (ভেরি)"),
            Triple("W (ডাব্লিউ)", "ও-আ ধ্বনি (ঠোঁট গোল)", "Water (ওয়াটার), Work (ওয়ার্ক)"),
            Triple("X (এক্স)", "ক্স / জ-ধ্বনি", "Box (বক্স), Exam (এক্সাম)"),
            Triple("Y (ওয়াই)", "ইয় / ই ধ্বনি", "Yes (ইয়েস), Happy (হ্যাপি)"),
            Triple("Z (জেড / জি)", "জ-ধ্বনি (কম্পনযুক্ত)", "Zero (জিরো), Zebra (জেব্রা)")
        )
        pronData.forEachIndexed { idx, (letterPron, rule, example) ->
            list.add(
                LessonCardEntity(
                    lessonId = "lesson_pronunciation",
                    cardOrder = idx + 1,
                    cardType = "ALPHABET",
                    primaryText = letterPron,
                    secondaryText = rule,
                    bengaliMeaning = example,
                    exampleEn = example.substringBefore(" ("),
                    exampleBn = example,
                    cardColorIndex = idx % 8
                )
            )
        }

        // 4. Vocabulary A-M
        val vocabAM = listOf(
            listOf("Answer", "অ্যান্সার", "উত্তর", "Please give me the answer.", "দয়া করে আমাকে উত্তরটি দিন।"),
            listOf("Brother", "ব্রাদার", "ভাই", "My brother is a student.", "আমার ভাই একজন ছাত্র।"),
            listOf("Clean", "ক্লিন", "পরিষ্কার", "Keep your room clean.", "তোমার ঘর পরিষ্কার রাখো।"),
            listOf("Doctor", "ডক্টর", "ডাক্তার", "The doctor is very kind.", "ডাক্তার সাহেব খুব দয়ালু।"),
            listOf("Early", "আর্লি", "তাড়াতাড়ি / সকালে", "I wake up early.", "আমি ভোরে ঘুম থেকে উঠি।"),
            listOf("Family", "ফ্যামিলি", "পরিবার", "I love my family.", "আমি আমার পরিবারকে ভালোবাসি।"),
            listOf("Good", "গুড", "ভালো", "She is a good friend.", "সে একজন ভালো বন্ধু।"),
            listOf("Happy", "হ্যাপি", "খুশি / সুখী", "Today I am very happy.", "আজ আমি খুব খুশি।"),
            listOf("Important", "ইম্পর্ট্যান্ট", "গুরুত্বপূর্ণ", "English is very important.", "ইংরেজি খুব গুরুত্বপূর্ণ।"),
            listOf("Journey", "জার্নি", "ভ্রমণ / যাত্রা", "Have a safe journey.", "তোমার যাত্রা নিরাপদ হোক।"),
            listOf("Knowledge", "নলেজ", "জ্ঞান", "Reading gives us knowledge.", "পড়া আমাদের জ্ঞান দেয়।"),
            listOf("Language", "ল্যাঙ্গুয়েজ", "ভাষা", "Bengali is our mother language.", "বাংলা আমাদের মাতৃভাষা।"),
            listOf("Morning", "মর্নিং", "সকাল", "Good morning, everyone!", "সুপ্রভাত, সবাইকে!")
        )
        vocabAM.forEachIndexed { idx, v ->
            list.add(
                LessonCardEntity(
                    lessonId = "lesson_vocab_am",
                    cardOrder = idx + 1,
                    cardType = "VOCAB",
                    primaryText = v[0],
                    secondaryText = "উচ্চারণ: ${v[1]}",
                    bengaliMeaning = v[2],
                    exampleEn = v[3],
                    exampleBn = v[4],
                    cardColorIndex = idx % 8
                )
            )
        }

        // 5. Vocabulary N-Z
        val vocabNZ = listOf(
            listOf("Nature", "নেচার", "প্রকৃতি", "Nature is full of beauty.", "প্রকৃতি সৌন্দর্যে ভরা।"),
            listOf("Office", "অফিস", "কার্যালয় / অফিস", "Father goes to the office.", "বাবা অফিসে যান।"),
            listOf("Practice", "প্র্যাকটিস", "অনুশীলন", "Practice makes a man perfect.", "অনুশীলন মানুষকে দক্ষ করে।"),
            listOf("Question", "কোয়েশ্চেন", "প্রশ্ন", "Ask a simple question.", "একটি সহজ প্রশ্ন করো।"),
            listOf("Respect", "রেসপেক্ট", "সম্মান / শ্রদ্ধা", "Respect your parents.", "মা-বাবাকে সম্মান করো।"),
            listOf("School", "স্কুল", "বিদ্যালয়", "Children go to school.", "শিশুরা স্কুলে যায়।"),
            listOf("Time", "টাইম", "সময়", "Time is very valuable.", "সময় খুব মূল্যবান।"),
            listOf("Understand", "আন্ডারস্ট্যান্ড", "বোঝা", "Now I understand clearly.", "এখন আমি পরিষ্কার বুঝতে পারছি।"),
            listOf("Village", "ভিলেজ", "গ্রাম", "Our village is green.", "আমাদের গ্রাম সবুজ।"),
            listOf("Water", "ওয়াটার", "পানি", "Drink clean water daily.", "প্রতিদিন বিশুদ্ধ পানি পান করো।"),
            listOf("X-ray", "এক্স-রে", "রঞ্জনরশ্মি / এক্স-রে", "The doctor checked the X-ray.", "ডাক্তার এক্স-রে পরীক্ষা করলেন।"),
            listOf("Young", "ইয়াং", "তরুণ / যুবক", "He is young and energetic.", "সে তরুণ এবং উদ্যমী।"),
            listOf("Zero", "জিরো", "শূন্য", "Start from zero with confidence.", "আত্মবিশ্বাস নিয়ে শূন্য থেকে শুরু করো।")
        )
        vocabNZ.forEachIndexed { idx, v ->
            list.add(
                LessonCardEntity(
                    lessonId = "lesson_vocab_nz",
                    cardOrder = idx + 1,
                    cardType = "VOCAB",
                    primaryText = v[0],
                    secondaryText = "উচ্চারণ: ${v[1]}",
                    bengaliMeaning = v[2],
                    exampleEn = v[3],
                    exampleBn = v[4],
                    cardColorIndex = idx % 8
                )
            )
        }

        // 6. Basic Verbs A-Z
        val verbsAZ = listOf(
            listOf("Ask — Asked — Asked", "জিজ্ঞাসা করা", "Ask me anything.", "আমাকে যেকোনো কিছু জিজ্ঞাসা করো।"),
            listOf("Buy — Bought — Bought", "কেনা", "I want to buy a book.", "আমি একটি বই কিনতে চাই।"),
            listOf("Come — Came — Come", "আসা", "Please come here.", "দয়া করে এখানে এসো।"),
            listOf("Do — Did — Done", "করা", "I do my homework daily.", "আমি প্রতিদিন বাড়ির কাজ করি।"),
            listOf("Eat — Ate — Eaten", "খাওয়া", "We eat rice every day.", "আমরা প্রতিদিন ভাত খাই।"),
            listOf("Find — Found — Found", "খুঁজে পাওয়া", "I found my keys.", "আমি আমার চাবি খুঁজে পেয়েছি।"),
            listOf("Go — Went — Gone", "যাওয়া", "They go to market.", "তারা বাজারে যায়।"),
            listOf("Help — Helped — Helped", "সাহায্য করা", "Please help the poor.", "দয়া করে গরিবদের সাহায্য করো।"),
            listOf("Know — Knew — Known", "জানা / চেনা", "I know his name.", "আমি তার নাম জানি।"),
            listOf("Learn — Learned — Learned", "শেখা", "I am learning English.", "আমি ইংরেজি শিখছি।"),
            listOf("Make — Made — Made", "তৈরি করা", "She makes tea.", "সে চা তৈরি করে।"),
            listOf("Need — Needed — Needed", "প্রয়োজন হওয়া", "I need some rest.", "আমার কিছু বিশ্রাম দরকার।"),
            listOf("Open — Opened — Opened", "খোলা", "Open the window, please.", "দয়া করে জানালাটি খোলো।"),
            listOf("Play — Played — Played", "খেলা করা", "Boys play football.", "ছেলেরা ফুটবল খেলে।"),
            listOf("Read — Read — Read", "পড়া", "Read this page carefully.", "এই পৃষ্ঠাটি মনোযোগ দিয়ে পড়ো।"),
            listOf("Speak — Spoke — Spoken", "কথা বলা", "Speak slowly and clearly.", "ধীরে এবং স্পষ্টভাবে বলো।"),
            listOf("Take — Took — Taken", "নেওয়া", "Take your umbrella.", "তোমার ছাতাটি নাও।"),
            listOf("Use — Used — Used", "ব্যবহার করা", "Use simple words.", "সহজ শব্দ ব্যবহার করো।"),
            listOf("Walk — Walked — Walked", "হাঁটা", "I walk in the morning.", "আমি সকালে হাঁটি।"),
            listOf("Write — Wrote — Written", "লেখা", "Write five sentences daily.", "প্রতিদিন পাঁচটি বাক্য লেখো।")
        )
        verbsAZ.forEachIndexed { idx, v ->
            list.add(
                LessonCardEntity(
                    lessonId = "lesson_verbs_az",
                    cardOrder = idx + 1,
                    cardType = "VERB",
                    primaryText = v[0],
                    secondaryText = "V1 (Present) — V2 (Past) — V3 (Past Participle)",
                    bengaliMeaning = v[1],
                    exampleEn = v[2],
                    exampleBn = v[3],
                    cardColorIndex = idx % 8
                )
            )
        }

        return list
    }
}
