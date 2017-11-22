package sg.vinova.besttrip

import android.app.Application
import sg.vinova.besttrip.dagger.component.BComponent
import sg.vinova.besttrip.dagger.component.DaggerBComponent
import sg.vinova.besttrip.dagger.module.BModule

/**
 * Created by hanah on 11/22/17.
 */
class BApplication : Application() {

    lateinit var component: BComponent


    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerBComponent.builder()
                .bModule(BModule(this)).build()
        component.inject(this)
    }

    companion object {
        lateinit var instance: BApplication
    }

}
