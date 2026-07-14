package com.nutrition.nutritrackai.di


import android.content.Context
import androidx.room.Room
import com.nutrition.nutritrackai.data.local.dao.MealDao
import com.nutrition.nutritrackai.data.local.dao.WaterDao
import com.nutrition.nutritrackai.data.local.database.NutriDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NutriDatabase {

        return Room.databaseBuilder(
            context,
            NutriDatabase::class.java,
            "nutri_database"
        ) .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    @Singleton
    fun provideMealDao(
        database: NutriDatabase
    ): MealDao {

        return database.mealDao()

    }

    @Provides
    @Singleton
    fun provideWaterDao(
        database: NutriDatabase
    ): WaterDao {

        return database.waterDao()

    }


}