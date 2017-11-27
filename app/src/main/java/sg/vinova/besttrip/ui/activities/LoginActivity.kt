package sg.vinova.besttrip.ui.activities

import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.ui.fragments.MenuFragment
import sg.vinova.besttrip.ui.fragments.SplashFragment

/**
 * Created by Hanah on 11/22/2017.
 */
class LoginActivity : BaseBActivity() {
    override fun replaceFragmentId(): Int = R.id.fragmentContainer

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        setActionBar(toolbar)
        changeFragment(SplashFragment.newInstance(), false)
        replaceFragment(MenuFragment.newInstance(), R.id.leftContainer)
    }

    fun setTitle(string: String) {
        toolbar.setToolbarTitle(string)
    }

    fun setLeftIcon(int: Int?) {
        if (int == null) toolbar.hideLeftIcon()
        else toolbar.setLeftIcon(int)
    }

    fun setRightIcon(int: Int?) {
        if (int == null) toolbar.hideRightIcon()
        else toolbar.setRightIcon(int)
    }

    fun setToolbarListener(listener: BaseListener.OnToolbarClickListener) {
        toolbar.listener = listener
    }

    fun hideToolbar() {
        if (toolbar.visibility == View.VISIBLE)
            toolbar.visibility = View.GONE
    }

    fun showToolbar() {
        if (toolbar.visibility == View.GONE)
            toolbar.visibility = View.VISIBLE
    }
}
