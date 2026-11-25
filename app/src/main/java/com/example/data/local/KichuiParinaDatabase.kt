package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.LearningDao
import com.example.data.local.entity.LessonCardEntity
import com.example.data.local.entity.LessonEntity
import com.example.data.local.entity.LessonProgressEntity
import com.example.data.local.entity.PdfBookmarkEntity
import com.example.data.local.entity.QuizQuestionEntity
import com.example.data.local.entity.QuizResultEntity

@Database(
    entities = [
        LessonEntity::class,
        LessonCardEntity::class,
        QuizQuestionEntity::class,
        LessonProgressEntity::class,
        QuizResultEntity::class,
        PdfBookmarkEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class KichuiParinaDatabase : RoomDatabase() {
    abstract fun learningDao(): LearningDao

    companion object {
        @Volatile
        private var INSTANCE: KichuiParinaDatabase? = null

        fun getInstance(context: Context): KichuiParinaDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    KichuiParinaDatabase::class.java,
                    "kichui_parina_learning.db"
                )
                    .fallbackToDestructiveMigration(dropAllTables = false)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
