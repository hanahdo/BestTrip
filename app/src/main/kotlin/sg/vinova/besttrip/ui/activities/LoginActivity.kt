package sg.vinova.besttrip.ui.activities

import kotlinx.android.synthetic.main.activity_login.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.ui.fragments.MenuFragment
import sg.vinova.besttrip.ui.fragments.SplashFragment

open class LoginActivity : BaseBActivity() {
    override fun replaceFragmentId(): Int = R.id.fragmentContainer

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        setActionBar(toolbar)
        changeFragment(SplashFragment.newInstance())
        replaceFragment(MenuFragment.newInstance(), R.id.leftContainer)
    }
}
