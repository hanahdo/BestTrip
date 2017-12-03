package sg.vinova.besttrip.ui.activities

import android.Manifest
import android.support.v4.app.ActivityCompat
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.services.BaseCallback
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.ui.fragments.MenuFragment
import sg.vinova.besttrip.ui.fragments.result.LandingFragment
import sg.vinova.besttrip.utils.LogUtils
import sg.vinova.besttrip.utils.PermissionUtils


/**
 * Created by Hanah on 11/23/2017.
 */
class MapActivity : BaseBActivity(), DrawerLayout.DrawerListener, ActivityCompat.OnRequestPermissionsResultCallback, BaseCallback.PermissionResultCallback {
    private lateinit var permissionUtils: PermissionUtils
    private var permissions: ArrayList<String> = ArrayList()

    override fun replaceFragmentId(): Int = R.id.fragmentContainer

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        setActionBar(toolbar)
        changeFragment(LandingFragment.newInstance(), false)
        replaceFragment(MenuFragment.newInstance(), R.id.leftContainer)
        requestLocationPermission()

        drawer.addDrawerListener(this)
    }

    private fun requestLocationPermission() {
        permissionUtils = PermissionUtils(applicationContext)

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)

        permissionUtils.checkPermission(permissions, "Need GPS permission for getting your location", 1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    override fun permissionGranted(requestCode: Int) {
        LogUtils.bInfo("PERMISSION - GRANTED")
    }

    override fun partialPermissionGranted(requestCode: Int, grantedPermissions: ArrayList<String>) {
        LogUtils.bInfo("PERMISSION - PARTIALLY GRANTED")
    }

    override fun permissionDenied(requestCode: Int) {
        LogUtils.bInfo("PERMISSION - DENIED")
    }

    override fun neverAskAgain(requestCode: Int) {
        LogUtils.bInfo("PERMISSION - NEVER ASK AGAIN")
    }

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
}