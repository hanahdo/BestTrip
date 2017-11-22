package sg.vinova.besttrip.dagger.component

import dagger.Component
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.dagger.module.BModule
import javax.inject.Singleton

/**
 * Created by hanah on 11/22/17.
 */
@Singleton
@Component(modules = arrayOf(BModule::class))
interface BComponent {
    fun inject(app: BApplication)
    fun inject(activity: BaseBActivity)
}
