package sg.vinova.besttrip.ui.activities

import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.ui.fragments.MapFragment
import sg.vinova.besttrip.ui.fragments.MenuFragment


/**
 * Created by Hanah on 11/23/2017.
 */
class MapActivity : BaseBActivity(), DrawerLayout.DrawerListener {
    override fun onDrawerStateChanged(newState: Int) {

    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

    }

    override fun onDrawerClosed(drawerView: View) {
        fragmentContainer.isEnabled = true
    }

    override fun onDrawerOpened(drawerView: View) {
        fragmentContainer.isEnabled = false
    }

    override fun replaceFragmentId(): Int = R.id.fragmentContainer

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        setActionBar(toolbar)
        changeFragment(MapFragment.newInstance(), false)
        replaceFragment(MenuFragment.newInstance(), R.id.leftContainer)
        requestLocationPermission()

        drawer.addDrawerListener(this)
    }

    private fun requestLocationPermission() {

    }

    fun showMenu() {
        if (drawer == null) return
        drawer.openDrawer(Gravity.START)
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