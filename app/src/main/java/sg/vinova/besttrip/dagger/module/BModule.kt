package sg.vinova.besttrip.dagger.module

import android.os.Handler
import dagger.Module
import dagger.Provides
import sg.vinova.besttrip.BApplication
import javax.inject.Singleton

/**
 * Created by hanah on 11/22/17.
 */
@Module
class BModule(private val app:BApplication) {
    @Provides
    @Singleton
    fun getApp() = app

    @Provides
    @Singleton
    fun getHandler() = Handler()

    @Provides
    fun getContext() = app.applicationContext!!
}