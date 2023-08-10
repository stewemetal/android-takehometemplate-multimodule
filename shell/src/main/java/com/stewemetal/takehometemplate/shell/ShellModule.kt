package com.stewemetal.takehometemplate.shell

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.squareup.moshi.Moshi
import com.stewemetal.takehometemplate.shell.architecture.DefaultDispatcherProvider
import com.stewemetal.takehometemplate.shell.architecture.DispatcherProvider
import com.stewemetal.takehometemplate.shell.database.TakeHomeTemplateDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@ComponentScan
class ShellModule {

    @Single
    fun moshi(): Moshi =
        Moshi
            .Builder()
            .build()

    @Single
    fun httpClient(): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    }
            )
            // Add more interceptors here
            .build()

    @Single
    fun retrofit(
        httpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://takehometemplate.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()

    @Single
    fun database(
        context: Context,
    ): RoomDatabase =
        Room
            .databaseBuilder(
                context = context,
                klass = TakeHomeTemplateDatabase::class.java,
                name = "app-db",
            )
            .build()

    @Single
    fun dispatcherProvider(): DispatcherProvider =
        DefaultDispatcherProvider()
}
