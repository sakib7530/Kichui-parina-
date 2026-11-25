package com.example

import android.app.Application
import com.example.data.local.KichuiParinaDatabase
import com.example.data.preferences.UserPreferencesRepository
import com.example.data.repository.LearningRepository

class AppContainer(application: Application) {
    private val database: KichuiParinaDatabase by lazy {
        KichuiParinaDatabase.getInstance(application)
    }

    val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(application)
    }

    val learningRepository: LearningRepository by lazy {
        LearningRepository(
            dao = database.learningDao(),
            prefsRepo = userPreferencesRepository
        )
    }
}

class KichuiParinaApp : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
