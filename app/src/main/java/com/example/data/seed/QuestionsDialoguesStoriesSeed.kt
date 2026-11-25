package com.example.data.seed

import com.example.data.local.entity.LessonCardEntity
import com.example.data.local.entity.LessonEntity

object QuestionsDialoguesStoriesSeed {

    fun lessons(): List<LessonEntity> = listOf(
        LessonEntity(
            id = "lesson_english_questions",
            categoryId = "english_questions",
            categoryTitleEn = "English Questions",
            categoryTitleBn = "সহজ প্রশ্ন করা (Yes/No Questions)",
            orderIndex = 14,
            titleEn = "How to Ask Yes/No Questions",
            titleBn = "Am/Is/Are, Do/Does, Did, Can দিয়ে প্রশ্ন তৈরি",
            bengaliExplanation = "যেকোনো বাক্যের Auxiliary Verb (am, is, are, do, does, did, can, will)-কে Subject-এর সামনে বসালেই 'হ্যাঁ বা না' উত্তরযোগ্য প্রশ্ন তৈরি হয়ে যায়।",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "question"
        ),
        LessonEntity(
            id = "lesson_wh_questions",
            categoryId = "wh_questions",
            categoryTitleEn = "WH Questions",
            categoryTitleBn = "WH প্রশ্ন (What, Where, Why...)",
            orderIndex = 15,
            titleEn = "Mastering WH Questions",
            titleBn = "What, Who, Where, When, Why, How ব্যবহার",
            bengaliExplanation = "তথ্য জানতে হলে প্রশ্নবোধক শব্দের (WH-word) পরেই Auxiliary Verb এবং তারপর Subject বসাতে হয়। সূত্র: WH + Auxiliary + Subject + Verb?",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "wh"
        ),
        LessonEntity(
            id = "lesson_advanced_questions",
            categoryId = "advanced_questions",
            categoryTitleEn = "Advanced Questions",
            categoryTitleBn = "বিস্তারিত ও ভদ্র প্রশ্ন (Advanced)",
            orderIndex = 16,
            titleEn = "Polite & Detailed Questions",
            titleBn = "How much/many, How long ও ভদ্র অনুরোধমূলক প্রশ্ন",
            bengaliExplanation = "দোকানে দাম জিজ্ঞাসা করা, সময়কাল জানা বা কাউকে ভদ্রভাবে সাহায্য চাওয়ার জন্য How much, How many, How long এবং Could you / Would you ব্যবহার করা হয়।",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "advanced"
        ),
        LessonEntity(
            id = "lesson_dialogues",
            categoryId = "dialogues",
            categoryTitleEn = "English Dialogues",
            categoryTitleBn = "দৈনন্দিন কথোপকথন (Dialogues)",
            orderIndex = 17,
            titleEn = "Real-Life English Conversations",
            titleBn = "বাজার, রাস্তা জিজ্ঞাসা ও কুশল বিনিময়ের সংলাপ",
            bengaliExplanation = "বাস্তব জীবনে ইংরেজিতে কথা বলার জন্য ছোট ছোট সংলাপ মুখস্থ ও অনুশীলন করা সবচেয়ে কার্যকর উপায়। নিচে বাংলা অর্থসহ সহজ কথোপকথন দেওয়া হলো।",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "chat"
        ),
        LessonEntity(
            id = "lesson_story_reading",
            categoryId = "story_reading",
            categoryTitleEn = "Story Reading",
            categoryTitleBn = "গল্প পড়ে ইংরেজি শেখা (Stories)",
            orderIndex = 18,
            titleEn = "Short Stories with Bengali Meaning",
            titleBn = "ছোট শিক্ষণীয় গল্প ও বাংলা অনুবাদ",
            bengaliExplanation = "ছোট গল্প পড়লে নতুন শব্দ, বাক্য গঠন এবং পড়ার গতি বাড়ে। প্রতিটি গল্পের সাথে সহজ বাংলা অনুবাদ ও নৈতিক শিক্ষা দেওয়া হলো।",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "story"
        ),
        LessonEntity(
            id = "lesson_self_intro",
            categoryId = "self_introduction",
            categoryTitleEn = "নিজের পরিচয় দেওয়া (Self Intro)",
            categoryTitleBn = "নিজের পরিচয় দেওয়া (Self Introduction)",
            orderIndex = 19,
            titleEn = "How to Introduce Yourself",
            titleBn = "ইংরেজিতে আত্মবিশ্বাসের সাথে নিজের পরিচয় দেওয়া",
            bengaliExplanation = "কারো সাথে প্রথম দেখা হলে, ক্লাসে বা ইন্টারভিউতে কীভাবে সুন্দর ও গোছানোভাবে নিজের নাম, ঠিকানা, পড়াশোনা, পরিবার ও লক্ষ্য বলবেন তা নিচের ধাপগুলোতে দেখুন।",
            contentSource = AlphabetAndVocabSeed.SOURCE_STARTER,
            pdfPageNote = AlphabetAndVocabSeed.PDF_NOTE_UNAVAILABLE,
            iconKey = "person"
        )
    )

    fun cards(): List<LessonCardEntity> {
        val list = mutableListOf<LessonCardEntity>()

        // 14. English Questions
        val eq = listOf(
            listOf("Are you + ...?", "তুমি কি...?", "Are you ready to start?", "তুমি কি শুরু করতে প্রস্তুত?"),
            listOf("Is he / she + ...?", "সে কি...?", "Is she your sister?", "সে কি তোমার বোন?"),
            listOf("Do you + V1...?", "তুমি কি (অভ্যাসগত কাজ) করো?", "Do you understand English?", "তুমি কি ইংরেজি বোঝো?"),
            listOf("Does he/she + V1...?", "সে কি... করে?", "Does he live near here?", "সে কি এর কাছাকাছি থাকে?"),
            listOf("Did you + V1...?", "তুমি কি (অতীতে) করেছিলে?", "Did you finish your work?", "তুমি কি তোমার কাজ শেষ করেছিলে?"),
            listOf("Can you + V1...?", "তুমি কি ... করতে পারো?", "Can you help me, please?", "তুমি কি দয়া করে আমাকে সাহায্য করতে পারো?")
        )
        eq.forEachIndexed { i, c ->
            list.add(LessonCardEntity(lessonId = "lesson_english_questions", cardOrder = i + 1, cardType = "PATTERN", primaryText = c[0], secondaryText = c[1], bengaliMeaning = c[3], exampleEn = c[2], exampleBn = c[3], cardColorIndex = i % 8))
        }

        // 15. WH Questions
        val wh = listOf(
            listOf("What (কী)", "বস্তু বা বিষয় জানতে", "What is your name?", "তোমার নাম কী?"),
            listOf("Who (কে / কারা)", "ব্যক্তি জানতে", "Who is your English teacher?", "তোমার ইংরেজি শিক্ষক কে?"),
            listOf("Where (কোথায়)", "স্থান জানতে", "Where do you live?", "তুমি কোথায় থাকো?"),
            listOf("When (কখন)", "সময় জানতে", "When do you wake up?", "তুমি কখন ঘুম থেকে ওঠো?"),
            listOf("Why (কেন)", "কারণ জানতে", "Why are you learning English?", "তুমি কেন ইংরেজি শিখছো?"),
            listOf("Which (কোনটি)", "পছন্দ বা নির্দিষ্ট কিছু জানতে", "Which book do you like?", "তুমি কোন বইটি পছন্দ করো?"),
            listOf("Whose (কার)", "মালিকানা জানতে", "Whose pen is this?", "এটি কার কলম?"),
            listOf("How (কীভাবে / কেমন)", "পদ্ধতি বা অবস্থা জানতে", "How are you today?", "আজ তুমি কেমন আছো?")
        )
        wh.forEachIndexed { i, c ->
            list.add(LessonCardEntity(lessonId = "lesson_wh_questions", cardOrder = i + 1, cardType = "PATTERN", primaryText = c[0], secondaryText = c[1], bengaliMeaning = c[3], exampleEn = c[2], exampleBn = c[3], cardColorIndex = i % 8))
        }

        // 16. Advanced Questions
        val adv = listOf(
            listOf("How much + Uncountable?", "কতটুকু / দাম কত?", "How much is this shirt?", "এই শার্টটির দাম কত?"),
            listOf("How many + Countable?", "কতগুলো (গণনাযোগ্য)?", "How many brothers do you have?", "তোমার কয়জন ভাই আছে?"),
            listOf("How long...?", "কতক্ষণ / কতদিন?", "How long have you lived here?", "তুমি এখানে কতদিন ধরে থাকো?"),
            listOf("How often...?", "কতবার / কতদিন পর পর?", "How often do you practice?", "তুমি কতবার অনুশীলন করো?"),
            listOf("Could you please + V1?", "ভদ্রভাবে অনুরোধ করা", "Could you please repeat that?", "আপনি কি দয়া করে কথাটি আবার বলবেন?"),
            listOf("Would you like + ...?", "ভদ্রভাবে কিছু অফার করা", "Would you like some water?", "আপনি কি একটু পানি নেবেন?")
        )
        adv.forEachIndexed { i, c ->
            list.add(LessonCardEntity(lessonId = "lesson_advanced_questions", cardOrder = i + 1, cardType = "PATTERN", primaryText = c[0], secondaryText = c[1], bengaliMeaning = c[3], exampleEn = c[2], exampleBn = c[3], cardColorIndex = i % 8))
        }

        // 17. Dialogues
        val dialogues = listOf(
            listOf(
                "সংলাপ ১: কুশল বিনিময় (Greeting)",
                "দুই বন্ধুর দেখা হওয়ার পর কথোপকথন",
                "আসসালামু আলাইকুম / হ্যালো, কেমন আছো?",
                "A: Hello! How are you?\nB: I am fine, thank you. And you?\nA: I am doing great! What are you doing today?\nB: I am practicing English.",
                "A: হ্যালো! তুমি কেমন আছো?\nB: আমি ভালো আছি, ধন্যবাদ। আর তুমি?\nA: আমি দারুণ আছি! আজ তুমি কী করছো?\nB: আমি ইংরেজি অনুশীলন করছি।"
            ),
            listOf(
                "সংলাপ ২: দোকানে কেনাকাটা (At a Shop)",
                "দোকানদারের সাথে দাম ও জিনিস নিয়ে কথা",
                "দাম জিজ্ঞাসা ও কেনাকাটা",
                "Customer: Excuse me, how much is this notebook?\nShopkeeper: It is fifty taka.\nCustomer: Please give me two notebooks.\nShopkeeper: Here you are. Thank you!",
                "ক্রেতা: শুনুন, এই খাতাটির দাম কত?\nদোকানদার: এটি পঞ্চাশ টাকা।\nক্রেতা: দয়া করে আমাকে দুটি খাতা দিন।\nদোকানদার: এই নিন। ধন্যবাদ!"
            ),
            listOf(
                "সংলাপ ৩: রাস্তা জিজ্ঞাসা (Asking Directions)",
                "অপরিচিত জায়গায় ঠিকানা খুঁজে নেওয়া",
                "ঠিকানা জানতে চাওয়া",
                "Traveler: Excuse me, where is the nearest bus stop?\nLocal: Go straight and turn left.\nTraveler: Is it far from here?\nLocal: No, just five minutes on foot.",
                "পথিক: মাফ করবেন, সবচেয়ে কাছের বাস স্টপ কোথায়?\nস্থানীয় ব্যক্তি: সোজা গিয়ে বামে ঘুরুন।\nপথিক: এটি কি এখান থেকে দূরে?\nস্থানীয় ব্যক্তি: না, হেঁটে মাত্র পাঁচ মিনিট।"
            )
        )
        dialogues.forEachIndexed { i, d ->
            list.add(LessonCardEntity(lessonId = "lesson_dialogues", cardOrder = i + 1, cardType = "DIALOGUE", primaryText = d[0], secondaryText = d[1], bengaliMeaning = d[2], exampleEn = d[3], exampleBn = d[4], cardColorIndex = i % 8))
        }

        // 18. Story Reading
        val stories = listOf(
            listOf(
                "Story 1: The Thirsty Crow (তৃষ্ণার্ত কাক)",
                "Moral: Where there is a will, there is a way (ইচ্ছা থাকলে উপায় হয়)",
                "ছোট গল্প — সরল অতীত কালে (Simple Past)",
                "One hot day, a crow was very thirsty. It flew here and there in search of water. At last, it saw a pitcher with very little water at the bottom. The crow could not reach the water. Then it saw some small stones nearby. It picked up the stones one by one and dropped them into the pitcher. Slowly, the water rose up. The crow drank the water and flew away happily.",
                "এক গরম দিনে একটি কাক খুব তৃষ্ণার্ত ছিল। সে পানির খোঁজে এদিক-ওদিক উড়ে বেড়াল। অবশেষে সে একটি কলসি দেখতে পেল যার নিচে খুব অল্প পানি ছিল। কাকটি পানির নাগাল পাচ্ছিল না। তখন সে কাছেই কিছু ছোট পাথর দেখতে পেল। সে একটি একটি করে পাথর তুলে কলসির মধ্যে ফেলল। ধীরে ধীরে পানি উপরে উঠে এল। কাকটি পানি পান করে আনন্দে উড়ে গেল।"
            ),
            listOf(
                "Story 2: Honest Woodcutter (সৎ কাঠুরে)",
                "Moral: Honesty is the best policy (সততাই সর্বোৎকৃষ্ট পন্থা)",
                "ছোট গল্প — সততার পুরস্কার",
                "Once a poor woodcutter was cutting wood near a river. Suddenly, his axe slipped from his hand and fell into the deep water. He became very sad because he had no money to buy a new axe. An angel appeared and brought a golden axe, then a silver axe. The honest woodcutter refused both and said those were not his. Finally, the angel brought his iron axe. Pleased with his honesty, the angel gave him all three axes.",
                "একদা এক দরিদ্র কাঠুরে নদীর ধারে কাঠ কাটছিল। হঠাৎ তার কুড়াল হাত থেকে ফসকে গভীর পানিতে পড়ে গেল। সে খুব দুঃখ পেল কারণ নতুন কুড়াল কেনার মতো টাকা তার ছিল না। তখন এক ফেরেশতা এসে একটি সোনার কুড়াল এবং পরে একটি রুপার কুড়াল নিয়ে এলেন। সৎ কাঠুরে দুটোই ফিরিয়ে দিয়ে বলল এগুলো তার নয়। অবশেষে ফেরেশতা তার লোহার কুড়ালটি আনলেন। তার সততায় খুশি হয়ে ফেরেশতা তাকে তিনটি কুড়ালই উপহার দিলেন।"
            ),
            listOf(
                "Story 3: Small Steps Every Day (প্রতিদিনের ছোট পদক্ষেপ)",
                "Moral: Consistency beats talent (নিয়মিত চেষ্টা সাফল্যের চাবিকাঠি)",
                "অনুপ্রেরণামূলক পাঠ",
                "Rafiq wanted to learn English, but he felt afraid to make mistakes. His teacher told him, 'Learn just five new words and three sentences every day.' Rafiq followed this advice strictly for three months. Soon he could read simple stories and speak with confidence. Nothing is impossible when you start step by step.",
                "রফিক ইংরেজি শিখতে চাইত, কিন্তু ভুল করার ভয়ে থাকত। তার শিক্ষক বললেন, 'প্রতিদিন মাত্র পাঁচটি নতুন শব্দ আর তিনটি বাক্য শেখো।' রফিক তিন মাস ধরে নিয়মিত এই পরামর্শ মেনে চলল। শীঘ্রই সে সহজ গল্প পড়তে এবং আত্মবিশ্বাসের সাথে কথা বলতে পারল। ধাপে ধাপে শুরু করলে কোনো কিছুই অসম্ভব নয়।"
            )
        )
        stories.forEachIndexed { i, s ->
            list.add(LessonCardEntity(lessonId = "lesson_story_reading", cardOrder = i + 1, cardType = "STORY", primaryText = s[0], secondaryText = s[1], bengaliMeaning = s[2], exampleEn = s[3], exampleBn = s[4], cardColorIndex = i % 8))
        }

        // 19. নিজের পরিচয় দেওয়া
        val intro = listOf(
            listOf("১. অভিবাদন ও নাম", "Greeting & Name", "Assalamu Alaikum / Hello everyone. My name is Sakib.", "আসসালামু আলাইকুম / হ্যালো সবাইকে। আমার নাম সাকিব।"),
            listOf("২. বয়স ও বাসস্থান", "Age & Hometown", "I am 20 years old. I live in Dhaka, Bangladesh.", "আমার বয়স ২০ বছর। আমি বাংলাদেশের ঢাকায় থাকি।"),
            listOf("৩. পড়াশোনা বা পেশা", "Study or Profession", "I am a student. / I work in a private company.", "আমি একজন ছাত্র। / আমি একটি বেসরকারি প্রতিষ্ঠানে কাজ করি।"),
            listOf("৪. পরিবার সম্পর্কে", "About Family", "There are four members in my family. My father is a businessman and my mother is a homemaker.", "আমার পরিবারে চারজন সদস্য। আমার বাবা একজন ব্যবসায়ী এবং মা গৃহিণী।"),
            listOf("৫. পছন্দ ও শখ", "Hobbies & Interests", "In my free time, I like reading books and learning new skills.", "অবসর সময়ে আমি বই পড়তে এবং নতুন দক্ষতা শিখতে পছন্দ করি।"),
            listOf("৬. লক্ষ্য ও সমাপ্তি", "Goal & Closing", "My goal is to speak English fluently. Thank you very much!", "আমার লক্ষ্য হলো সাবলীলভাবে ইংরেজিতে কথা বলা। আপনাকে অনেক ধন্যবাদ!")
        )
        intro.forEachIndexed { i, c ->
            list.add(LessonCardEntity(lessonId = "lesson_self_intro", cardOrder = i + 1, cardType = "PATTERN", primaryText = c[0], secondaryText = c[1], bengaliMeaning = c[3], exampleEn = c[2], exampleBn = c[3], cardColorIndex = i % 8))
        }

        return list
    }
}
