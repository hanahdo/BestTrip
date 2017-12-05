package sg.vinova.besttrip.ui.activities

import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.ui.fragments.MenuFragment
import sg.vinova.besttrip.ui.fragments.SplashFragment
import sg.vinova.besttrip.widgets.BToolbar
import sg.vinova.besttrip.widgets.dialogs.BSubmitDialog

/**
 * Created by Hanah on 11/22/2017.
 */
class LoginActivity : BaseBActivity() {
    lateinit var bToolbar: BToolbar

    override fun replaceFragmentId(): Int = R.id.fragmentContainer

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        setActionBar(toolbar)
        bToolbar = toolbar
        changeFragment(SplashFragment.newInstance(), false)
        replaceFragment(MenuFragment.newInstance(), R.id.leftContainer)
    }

    fun hideToolbar() {
        if (toolbar.visibility == View.VISIBLE)
            toolbar.visibility = View.GONE
    }

    fun showToolbar() {
        if (toolbar.visibility == View.GONE)
            toolbar.visibility = View.VISIBLE
    }

    fun showLoading() {
        fragmentContainer.isEnabled = false
        loadingBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        fragmentContainer.isEnabled = true
        loadingBar.visibility = View.INVISIBLE
    }
}
