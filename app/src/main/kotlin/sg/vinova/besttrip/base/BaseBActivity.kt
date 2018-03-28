package sg.vinova.besttrip.base

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.widgets.dialogs.BSubmitDialog

abstract class BaseBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        init()
    }

    override fun onBackPressed() {
        if (supportFragmentManager?.backStackEntryCount == 0)
            BSubmitDialog(this).apply {
                show()
                listener = View.OnClickListener {
                    dismiss()
                    if (it?.id == R.id.btnOk) this@BaseBActivity.finish()
                }
            }
        else super.onBackPressed()
    }

    abstract fun replaceFragmentId(): Int

    abstract fun getLayoutId(): Int

    abstract fun init()

    open fun changeFragment(fragment: BaseBFragment, addBackStack: Boolean = false, enableAnim: Boolean = false) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (!addBackStack) {
            val fragmentManager: FragmentManager = supportFragmentManager
            val backStackEntryCount: Int = fragmentManager.backStackEntryCount
            for (i: Int in 0 until backStackEntryCount) {
                fragmentManager.popBackStackImmediate()
            }
        }
        if (enableAnim) fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
        fragmentTransaction.replace(replaceFragmentId(), fragment, fragment.javaClass.simpleName)
        if (addBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    open fun replaceFragment(fragment: BaseBFragment, containerId: Int) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        fragmentTransaction.replace(containerId, fragment, fragment.javaClass.simpleName)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun changeActivity(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
        finish()
    }

    open fun getBaseActivity(): BaseBActivity = this

    fun showLoading() {
        fragmentContainer.isEnabled = false
        loadingBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        fragmentContainer.isEnabled = true
        loadingBar.visibility = View.INVISIBLE
    }

    fun showToolbar() {
        supportActionBar?.show()
    }

    fun hideToolbar() {
        supportActionBar?.hide()
    }
}
