package com.example.data.seed

import com.example.data.local.entity.LessonCardEntity
import com.example.data.local.entity.LessonEntity

object PatternsAndGrammarSeed {

    fun lessons(): List<LessonEntity> = listOf(
        LessonEntity(
            id = "lesson_sentence_patterns",
            categoryId = "sentence_patterns",
            categoryTitleEn = "English Sentence Patterns",
            categoryTitleBn = "ইংরেজি বাক্য গঠনের নিয়ম",
            orderIndex = 7,
            titleEn = "Core English Sentence Structure",
            titleBn = "Subject + Verb + Object মূল সূত্র",
            bengaliExplanation = "বাংলা বাক্যে ক্রিয়া শেষে বসে (যেমন: আমি ভাত খাই), কিন্তু ইংরেজিতে Subject (কর্তা)-এর ঠিক পরেই Verb (ক্রিয়া) বসে এবং শেষে Object বসে (I eat rice)। এই একটি নিয়ম বুঝলে হাজার হাজার বাক্য তৈরি করা যায়।",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "pattern"
        ),
        LessonEntity(
            id = "lesson_i_am_want_have",
            categoryId = "i_am_want_have",
            categoryTitleEn = "I am / I want / I have",
            categoryTitleBn = "I am / I want / I have ব্যবহার",
            orderIndex = 8,
            titleEn = "I am, I want & I have Mastery",
            titleBn = "পরিচয়, চাওয়া ও থাকা প্রকাশের ৩টি জাদুকরী গঠন",
            bengaliExplanation = "দৈনন্দিন কথাবার্তায় সবচেয়ে বেশি ব্যবহৃত হয়: I am (আমি হই/আছি), I want (আমি চাই), এবং I have (আমার আছে)। শুধু শেষের শব্দটি বদলেই অসংখ্য বাক্য বলা সম্ভব।",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "spark"
        ),
        LessonEntity(
            id = "lesson_i_am_verbing",
            categoryId = "i_am_verbing",
            categoryTitleEn = "I am + Verb-ing",
            categoryTitleBn = "I am + Verb-ing (চলমান কাজ)",
            orderIndex = 9,
            titleEn = "Continuous Actions: I am + Verb-ing",
            titleBn = "এই মুহূর্তে চলছে এমন কাজ বলার নিয়ম",
            bengaliExplanation = "আপনি এখন যা করছেন (করছি, খাচ্ছি, যাচ্ছি, শিখছি) তা ইংরেজিতে বলতে Subject + am/is/are + মূল Verb-এর সাথে ing যোগ করুন। যেমন: I am learning.",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "running"
        ),
        LessonEntity(
            id = "lesson_tenses",
            categoryId = "tenses_patterns",
            categoryTitleEn = "Present, Past & Future Patterns",
            categoryTitleBn = "বর্তমান, অতীত ও ভবিষ্যৎ কাল",
            orderIndex = 10,
            titleEn = "Present, Past & Future Made Simple",
            titleBn = "তিন সময়ের বাক্য একসাথে তুলনা করে শেখা",
            bengaliExplanation = "সময় অনুযায়ী Verb বদলে যায়। Present (বর্তমান) এ V1, Past (অতীত) এ V2, এবং Future (ভবিষ্যৎ) এ will + V1 বসে। নিচের কার্ডগুলোতে একই বাক্যের ৩ সময়ের রূপ দেখুন।",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "clock"
        ),
        LessonEntity(
            id = "lesson_prepositions",
            categoryId = "prepositions",
            categoryTitleEn = "English Prepositions",
            categoryTitleBn = "Prepositions (In, On, At, To...)",
            orderIndex = 11,
            titleEn = "Essential Prepositions Guide",
            titleBn = "সময় ও স্থান বোঝাতে Preposition-এর সঠিক ব্যবহার",
            bengaliExplanation = "কোনো কিছুর অবস্থান, দিক বা সময় বোঝাতে Noun-এর আগে Preposition (in, on, at, to, from, with, for) বসে। ছোট ভুল এড়াতে বাংলা অর্থসহ নিয়মগুলো দেখে নিন।",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "pin"
        ),
        LessonEntity(
            id = "lesson_adjectives",
            categoryId = "adjectives",
            categoryTitleEn = "English Adjectives",
            categoryTitleBn = "গুণবাচক শব্দ (Adjectives)",
            orderIndex = 12,
            titleEn = "Describing Words & Opposites",
            titleBn = "ব্যক্তি বা বস্তুর দোষ-গুণ ও অবস্থা প্রকাশক শব্দ",
            bengaliExplanation = "Adjective কোনো Noun বা Pronoun-এর গুণ, অবস্থা, সংখ্যা বা আকার বোঝায়। বিপরীত শব্দ (Opposite) জোড়ায় পড়লে একসাথে দুটি শব্দ দ্রুত মনে থাকে।",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "palette"
        ),
        LessonEntity(
            id = "lesson_sentence_making",
            categoryId = "sentence_making",
            categoryTitleEn = "Sentence Making",
            categoryTitleBn = "নিজে নিজে বাক্য তৈরি",
            orderIndex = 13,
            titleEn = "Step-by-Step Sentence Making",
            titleBn = "ছোট বাক্য থেকে বড় বাক্য বানানোর কৌশল",
            bengaliExplanation = "একটি ছোট বাক্যকে কীভাবে সময়, স্থান ও কারণ যোগ করে সুন্দর ও পূর্ণাঙ্গ বাক্যে রূপ দেওয়া যায় তা ধাপে ধাপে অনুশীলন করুন।",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "build"
        )
    )

    fun cards(): List<LessonCardEntity> {
        val list = mutableListOf<LessonCardEntity>()

        // 7. Sentence Patterns
        val patternCards = listOf(
            listOf("Subject + Verb + Object", "হ্যাঁ-বোধক (Affirmative)", "I read books.", "আমি বই পড়ি।"),
            listOf("Subject + do/does not + V1", "না-বোধক (Negative)", "I do not waste time.", "আমি সময় নষ্ট করি না।"),
            listOf("Subject + am/is/are + Noun/Adj", "অবস্থা বা পরিচয়", "She is very honest.", "সে খুবই সৎ।"),
            listOf("Subject + can + V1", "সক্ষমতা (পারা)", "I can speak English.", "আমি ইংরেজি বলতে পারি।"),
            listOf("Subject + cannot + V1", "অক্ষমতা (না পারা)", "He cannot swim.", "সে সাঁতার কাটতে পারে না।"),
            listOf("Subject + should + V1", "উপদেশ (উচিত)", "We should learn daily.", "আমাদের প্রতিদিন শেখা উচিত।")
        )
        patternCards.forEachIndexed { i, p ->
            list.add(LessonCardEntity(lessonId = "lesson_sentence_patterns", cardOrder = i + 1, cardType = "PATTERN", primaryText = p[0], secondaryText = p[1], bengaliMeaning = p[3], exampleEn = p[2], exampleBn = p[3], cardColorIndex = i % 8))
        }

        // 8. I am / I want / I have
        val iAmWantHave = listOf(
            listOf("I am + Noun/Adjective", "আমি হই / আমি আছি", "I am a beginner.", "আমি একজন নতুন শিক্ষার্থী।"),
            listOf("I am ready / tired / busy", "নিজের অবস্থা বলা", "I am ready to learn.", "আমি শেখার জন্য প্রস্তুত।"),
            listOf("I am not + ...", "না-বোধক রূপ", "I am not afraid.", "আমি ভীত নই।"),
            listOf("I want + Noun", "কোনো জিনিস চাওয়া", "I want a cup of tea.", "আমি এক কাপ চা চাই।"),
            listOf("I want to + Verb (V1)", "কোনো কাজ করতে চাওয়া", "I want to speak English.", "আমি ইংরেজিতে কথা বলতে চাই।"),
            listOf("I don't want to + V1", "না চাওয়া বোঝাতে", "I don't want to stop.", "আমি থামতে চাই না।"),
            listOf("I have + Noun", "অধিকারে থাকা বোঝাতে", "I have a dream.", "আমার একটি স্বপ্ন আছে।"),
            listOf("I have to + Verb (V1)", "বাধ্যবাধকতা (করতে হবে)", "I have to practice today.", "আজ আমাকে অনুশীলন করতে হবে।")
        )
        iAmWantHave.forEachIndexed { i, c ->
            list.add(LessonCardEntity(lessonId = "lesson_i_am_want_have", cardOrder = i + 1, cardType = "PATTERN", primaryText = c[0], secondaryText = c[1], bengaliMeaning = c[3], exampleEn = c[2], exampleBn = c[3], cardColorIndex = i % 8))
        }

        // 9. I am + Verb-ing
        val verbingCards = listOf(
            listOf("I am + Verb-ing", "আমি করছি (চলমান)", "I am reading a story.", "আমি একটি গল্প পড়ছি।"),
            listOf("You / We / They + are + Verb-ing", "বহুবচন ও You", "They are playing cricket.", "তারা ক্রিকেট খেলছে।"),
            listOf("He / She / Name + is + Verb-ing", "একবচন (তৃতীয় পুরুষ)", "She is writing a letter.", "সে একটি চিঠি লিখছে।"),
            listOf("I am not + Verb-ing", "চলমান না-বোধক", "I am not sleeping now.", "আমি এখন ঘুমাচ্ছি না।"),
            listOf("বানানের নিয়ম: e বাদ দিয়ে ing", "write → writing, make → making", "He is making coffee.", "সে কফি বানাচ্ছে।"),
            listOf("বানানের নিয়ম: দ্বিত্ব ব্যঞ্জনবর্ণ", "run → running, sit → sitting", "We are sitting together.", "আমরা একসাথে বসে আছি।")
        )
        verbingCards.forEachIndexed { i, c ->
            list.add(LessonCardEntity(lessonId = "lesson_i_am_verbing", cardOrder = i + 1, cardType = "PATTERN", primaryText = c[0], secondaryText = c[1], bengaliMeaning = c[3], exampleEn = c[2], exampleBn = c[3], cardColorIndex = i % 8))
        }

        // 10. Present, Past & Future
        val tenseCards = listOf(
            listOf("Present: I go to school.", "বর্তমান (সাধারণ অভ্যাস)", "আমি স্কুলে যাই।", "Subject + V1 (he/she হলে s/es)"),
            listOf("Past: I went to school.", "অতীত (শেষ হওয়া কাজ)", "আমি স্কুলে গিয়েছিলাম।", "Subject + V2 (Verb-এর Past রূপ)"),
            listOf("Future: I will go to school.", "ভবিষ্যৎ (আগামীতে হবে)", "আমি স্কুলে যাবো।", "Subject + will + V1"),
            listOf("Present: She eats rice.", "বর্তমান", "সে ভাত খায়।", "He/She-এর সাথে eat + s = eats"),
            listOf("Past: She ate rice.", "অতীত", "সে ভাত খেয়েছিল।", "Eat-এর V2 হলো Ate"),
            listOf("Future: She will eat rice.", "ভবিষ্যৎ", "সে ভাত খাবে।", "will-এর পরে সবসময় V1 বসে"),
            listOf("Present/Past/Future (Be Verb)", "am/is/are → was/were → will be", "I am happy / I was happy / I will be happy.", "আমি সুখী / সুখী ছিলাম / সুখী হবো।")
        )
        tenseCards.forEachIndexed { i, c ->
            list.add(LessonCardEntity(lessonId = "lesson_tenses", cardOrder = i + 1, cardType = "PATTERN", primaryText = c[0], secondaryText = c[1], bengaliMeaning = c[2], exampleEn = c[0], exampleBn = c[3], cardColorIndex = i % 8))
        }

        // 11. Prepositions
        val prepCards = listOf(
            listOf("IN (ভিতরে / বড় স্থান / মাস-সাল)", "স্থির অবস্থান বা বড় সময়", "I live in Bangladesh. / In May.", "আমি বাংলাদেশে থাকি। / মে মাসে।"),
            listOf("ON (উপরে / দিন-তারিখ)", "স্পর্শ করে উপরে বা নির্দিষ্ট দিন", "The book is on the table. / On Friday.", "বইটি টেবিলের উপরে আছে। / শুক্রবারে।"),
            listOf("AT (নির্দিষ্ট সময় / ছোট স্থান)", "ঘড়ির সময় বা ছোট জায়গা", "I wake up at 6 AM. / At home.", "আমি সকাল ৬টায় উঠি। / বাড়িতে।"),
            listOf("TO (দিকে / প্রতি)", "গন্তব্য বোঝাতে", "I go to office.", "আমি অফিসে যাই।"),
            listOf("FROM (থেকে / হতে)", "উৎস বা শুরুর জায়গা", "I came from Sylhet.", "আমি সিলেট থেকে এসেছি।"),
            listOf("WITH (সাথে / দিয়ে)", "সঙ্গী বা যন্ত্র বোঝাতে", "Come with me. / Write with a pen.", "আমার সাথে এসো। / কলম দিয়ে লেখো।"),
            listOf("FOR (জন্য)", "উদ্দেশ্য বা সময়কাল", "This gift is for you.", "এই উপহারটি তোমার জন্য।"),
            listOf("UNDER / BETWEEN", "নিচে / দুজনের মাঝে", "The cat is under the chair.", "বিড়ালটি চেয়ারের নিচে আছে।")
        )
        prepCards.forEachIndexed { i, c ->
            list.add(LessonCardEntity(lessonId = "lesson_prepositions", cardOrder = i + 1, cardType = "VOCAB", primaryText = c[0], secondaryText = c[1], bengaliMeaning = c[3], exampleEn = c[2], exampleBn = c[3], cardColorIndex = i % 8))
        }

        // 12. Adjectives
        val adjCards = listOf(
            listOf("Big ↔ Small", "বড় ↔ ছোট", "This is a big house, not small.", "এটি একটি বড় বাড়ি, ছোট নয়।"),
            listOf("Good ↔ Bad", "ভালো ↔ খারাপ", "Good habits bring success.", "ভালো অভ্যাস সাফল্য আনে।"),
            listOf("Hot ↔ Cold", "গরম ↔ ঠান্ডা", "I like hot tea and cold water.", "আমি গরম চা এবং ঠান্ডা পানি পছন্দ করি।"),
            listOf("Easy ↔ Difficult", "সহজ ↔ কঠিন", "English becomes easy with practice.", "অনুশীলনে ইংরেজি সহজ হয়ে যায়।"),
            listOf("Fast ↔ Slow", "দ্রুত ↔ ধীর", "The train is very fast.", "ট্রেনটি খুব দ্রুতগতির।"),
            listOf("Clean ↔ Dirty", "পরিষ্কার ↔ নোংরা", "Wear clean clothes.", "পরিষ্কার কাপড় পরো।"),
            listOf("Rich ↔ Poor", "ধনী ↔ গরিব", "Respect both rich and poor.", "ধনী ও গরিব উভয়কে সম্মান করো।"),
            listOf("Happy ↔ Sad", "খুশি ↔ দুঃখিত", "Don't be sad, stay happy.", "দুঃখিত হয়ো না, খুশি থাকো।")
        )
        adjCards.forEachIndexed { i, c ->
            list.add(LessonCardEntity(lessonId = "lesson_adjectives", cardOrder = i + 1, cardType = "VOCAB", primaryText = c[0], secondaryText = "বিপরীতার্থক জোড়া (Opposite Pair)", bengaliMeaning = c[1], exampleEn = c[2], exampleBn = c[3], cardColorIndex = i % 8))
        }

        // 13. Sentence Making
        val makingCards = listOf(
            listOf("ধাপ ১: মূল কাজ (Verb) বাছাই", "I + learn", "I learn.", "আমি শিখি।"),
            listOf("ধাপ ২: কী শিখি? (Object যোগ)", "I learn + English", "I learn English.", "আমি ইংরেজি শিখি।"),
            listOf("ধাপ ৩: কীভাবে শিখি? (Manner যোগ)", "I learn English + easily", "I learn English easily.", "আমি সহজে ইংরেজি শিখি।"),
            listOf("ধাপ ৪: কখন/কোথায়? (Time/Place যোগ)", "I learn English easily + every day", "I learn English easily every day.", "আমি প্রতিদিন সহজে ইংরেজি শিখি।"),
            listOf("উদাহরণ ড্রিল ২", "He + drinks + hot tea + in the morning", "He drinks hot tea in the morning.", "সে সকালে গরম চা পান করে।"),
            listOf("উদাহরণ ড্রিল ৩", "We + help + our friends + at school", "We help our friends at school.", "আমরা স্কুলে বন্ধুদের সাহায্য করি।")
        )
        makingCards.forEachIndexed { i, c ->
            list.add(LessonCardEntity(lessonId = "lesson_sentence_making", cardOrder = i + 1, cardType = "PATTERN", primaryText = c[0], secondaryText = c[1], bengaliMeaning = c[3], exampleEn = c[2], exampleBn = c[3], cardColorIndex = i % 8))
        }

        return list
    }
}
