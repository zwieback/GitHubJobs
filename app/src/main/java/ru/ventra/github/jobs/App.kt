package ru.ventra.github.jobs

import android.os.StrictMode
import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.ventra.github.jobs.di.*
import timber.log.Timber

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initCoroutineProperties()
        initKoin()
        turnOnStrictMode()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /**
     * Enable Debugging for Kotlin Coroutines in debug builds.
     *
     * Prints coroutine name when logging Thread.currentThread().name
     */
    private fun initCoroutineProperties() {
        System.setProperty("kotlinx.coroutines.debug", if (BuildConfig.DEBUG) "on" else "off")
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(persistenceModule)
            modules(networkModule)
            modules(localRepositoryModule)
            modules(networkRepositoryModule)
            modules(viewModelModule)
        }
    }

    private fun turnOnStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
        }
    }
}
