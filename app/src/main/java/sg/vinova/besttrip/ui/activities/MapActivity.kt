package sg.vinova.besttrip.ui.activities

import android.Manifest
import android.support.v4.app.ActivityCompat
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_login.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.services.BaseCallback
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.ui.fragments.MenuFragment
import sg.vinova.besttrip.ui.fragments.result.LandingFragment
import sg.vinova.besttrip.utils.LogUtils
import sg.vinova.besttrip.widgets.BToolbar


/**
 * Created by Hanah on 11/23/2017.
 */
class MapActivity : BaseBActivity(), DrawerLayout.DrawerListener {
    private lateinit var composite: CompositeDisposable
    private lateinit var rxPermission: RxPermissions
    lateinit var bToolbar: BToolbar

    override fun replaceFragmentId(): Int = R.id.fragmentContainer

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        setActionBar(toolbar)
        bToolbar = toolbar

        replaceFragment(MenuFragment.newInstance(), R.id.leftContainer)

        composite = CompositeDisposable()
        rxPermission = RxPermissions(this)
        requestLocationPermission()

        drawer.addDrawerListener(this)
    }

    private fun requestLocationPermission() {
        composite.add(rxPermission.request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION).subscribe({ isGranted ->
            if (isGranted)
                changeFragment(LandingFragment.newInstance(), false)
            else
                requestLocationPermission()
        }))
    }

    override fun onDestroy() {
        super.onDestroy()
        composite.clear()
        composite.dispose()
    }

    fun showMenu() {
        if (drawer == null) return
        drawer.openDrawer(Gravity.START)
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

    fun showLoading() {
        fragmentContainer.isEnabled = false
        loadingBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        fragmentContainer.isEnabled = true
        loadingBar.visibility = View.INVISIBLE
    }
}
