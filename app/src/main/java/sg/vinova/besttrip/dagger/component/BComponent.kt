package sg.vinova.besttrip.dagger.component

import dagger.Component
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.dagger.module.BModule
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.ui.fragments.MenuFragment
import sg.vinova.besttrip.ui.fragments.SplashFragment
import sg.vinova.besttrip.ui.fragments.account.ForgotFragment
import sg.vinova.besttrip.ui.fragments.account.LoginFragment
import sg.vinova.besttrip.ui.fragments.account.SignUpFragment
import sg.vinova.besttrip.ui.fragments.result.LandingFragment
import sg.vinova.besttrip.ui.fragments.result.MapFragment
import sg.vinova.besttrip.ui.fragments.result.SearchFragment
import javax.inject.Singleton

/**
 * Created by hanah on 11/22/17.
 */
@Singleton
@Component(modules = arrayOf(BModule::class))
interface BComponent {
    /**
     * Inject Application
     */
    fun inject(app: BApplication)

    /**
     *  Inject Base
     */
    fun inject(activity: BaseBActivity)

    fun inject(fragment: BaseBFragment)

    /**
     * Inject Activity
     */
    fun inject(activity: LoginActivity)

    fun inject(activity: MapActivity)

    /**
     * Inject fragment
     */
    fun inject(fragment: SplashFragment)

    fun inject(fragment: MenuFragment)

    fun inject(fragment: LoginFragment)

    fun inject(fragment: SignUpFragment)

    fun inject(fragment: ForgotFragment)

    fun inject(fragment: MapFragment)

    fun inject(fragment: LandingFragment)

    fun inject(fragment: SearchFragment)
}
