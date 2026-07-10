package com.nutrition.nutritrackai.di


import com.nutrition.nutritrackai.data.remote.api.FoodApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val logger = HttpLoggingInterceptor()

        logger.level = HttpLoggingInterceptor.Level.BODY

        return Retrofit.Builder()
            .baseUrl("https://world.openfoodfacts.org/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

    }

    @Provides
    @Singleton
    fun provideFoodApi(
        retrofit: Retrofit
    ): FoodApi {

        return retrofit.create(FoodApi::class.java)

    }

}