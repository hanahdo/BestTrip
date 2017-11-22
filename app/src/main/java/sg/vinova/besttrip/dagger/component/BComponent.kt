package sg.vinova.besttrip.dagger.component

import dagger.Component
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.dagger.module.BModule
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.ui.fragments.SplashFragment
import javax.inject.Singleton

/**
 * Created by hanah on 11/22/17.
 */
@Singleton
@Component(modules = arrayOf(BModule::class))
interface BComponent {
    // Inject Application
    fun inject(app: BApplication)

    // Inject Base
    fun inject(activity: BaseBActivity)

    fun inject(fragment: BaseBFragment)

    // Inject Activity
    fun inject(activity: LoginActivity)

    // Inject fragment
    fun inject(fragment: SplashFragment)
}
