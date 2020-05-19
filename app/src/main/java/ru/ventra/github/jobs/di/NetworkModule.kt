package ru.ventra.github.jobs.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ventra.github.jobs.BuildConfig
import ru.ventra.github.jobs.network.api.JobsApiService
import java.util.concurrent.TimeUnit

val networkModule = module {

    single<Gson> { GsonBuilder().create() }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(BuildConfig.NETWORK_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.BASE_API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .build()
    }

    single<JobsApiService> {
        get<Retrofit>().create(JobsApiService::class.java)
    }
}
