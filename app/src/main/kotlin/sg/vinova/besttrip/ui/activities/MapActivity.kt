package sg.vinova.besttrip.ui.activities

import android.Manifest
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.exts.debug
import sg.vinova.besttrip.ui.fragments.MenuFragment
import sg.vinova.besttrip.ui.fragments.result.LandingFragment

open class MapActivity : BaseBActivity(), DrawerLayout.DrawerListener {
    private lateinit var composite: CompositeDisposable
    private lateinit var rxPermission: RxPermissions

    override fun replaceFragmentId(): Int = R.id.fragmentContainer

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.map -> {
                supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                        ?.javaClass?.debug("On Maps icon menu click")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun init() {
        setActionBar(toolbar)

        replaceFragment(MenuFragment.newInstance(), R.id.leftContainer)

        composite = CompositeDisposable()
        rxPermission = RxPermissions(this)
        requestLocationPermission()

        drawer?.addDrawerListener(this)
    }

    private fun requestLocationPermission() {
        composite.add(rxPermission
                .request(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe({ isGranted ->
                    if (isGranted)
                        changeFragment(LandingFragment.newInstance())
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
        drawer?.openDrawer(Gravity.START)
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
