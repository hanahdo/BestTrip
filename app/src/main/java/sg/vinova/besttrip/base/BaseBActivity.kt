package sg.vinova.besttrip.base

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import sg.vinova.besttrip.R
import sg.vinova.besttrip.widgets.dialogs.BSubmitDialog

/**
 * Created by hanah on 11/22/17.
 */
abstract class BaseBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }

    abstract fun replaceFragmentId(): Int

    abstract fun getLayoutId(): Int

    abstract fun init()

    override fun onBackPressed() {
        BSubmitDialog(this).show().apply {
            DialogInterface.OnCancelListener { finish() }
        }
    }

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
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
        fragmentTransaction.replace(replaceFragmentId(), fragment, fragment.javaClass.simpleName)
        if (addBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    open fun replaceFragment(fragment: BaseBFragment, containerId: Int) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
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
