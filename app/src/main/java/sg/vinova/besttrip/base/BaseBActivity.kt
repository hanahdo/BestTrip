package sg.vinova.besttrip.base

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R

/**
 * Created by hanah on 11/22/17.
 */
abstract class BaseBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BApplication.instance.component.inject(this)
        inject()

        if (isFullScreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        setContentView(getLayoutId())

        init()
    }

    abstract fun replaceFragmentId(): Int

    abstract fun getLayoutId(): Int

    abstract fun inject()

    abstract fun init()

    abstract fun isFullScreen(): Boolean

    open fun changeFragment(fragment: BaseBFragment, addBackStack: Boolean) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        if (!addBackStack) {
            val fragmentManager: FragmentManager = supportFragmentManager
            val backStackEntryCount: Int = fragmentManager.backStackEntryCount
            for (i: Int in 0 until backStackEntryCount) {
                fragmentManager.popBackStackImmediate()
            }
        }
        fragmentTransaction.replace(replaceFragmentId(), fragment, fragment.javaClass.simpleName)
        if (addBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    open fun replaceFragment(fragment: BaseBFragment, containerId: Int) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment, fragment.javaClass.simpleName)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun changeActivity(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
        finish()
    }

    open fun getBaseActivity(): BaseBActivity = this
}
