package ch.heigvd.iict.sym.labo1

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IntroApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@IntroApp)
            modules(appDependencies)
        }
    }
}