package ru.ventra.github.jobs.di

import android.net.TrafficStats
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ventra.github.jobs.BuildConfig
import ru.ventra.github.jobs.network.api.JobsApiService
import ru.ventra.github.jobs.network.utils.DelegatingSocketFactory
import java.io.IOException
import java.net.Socket
import java.util.concurrent.TimeUnit
import javax.net.SocketFactory

val networkModule = module {

    single<Gson> {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    /**
     * [Original issue](https://github.com/square/okhttp/issues/3537)
     *
     * [Copied from here](https://github.com/Sage-Bionetworks/BridgeAndroidSDK/pull/151)
     */
    single<SocketFactory> {
        object : DelegatingSocketFactory(getDefault()) {
            @Throws(IOException::class)
            override fun configureSocket(socket: Socket): Socket {
                TrafficStats.setThreadStatsTag(100_000)
                return socket
            }
        }
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .socketFactory(get<SocketFactory>())
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
