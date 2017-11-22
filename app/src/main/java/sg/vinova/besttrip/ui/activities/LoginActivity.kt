package sg.vinova.besttrip.ui.activities

import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.widgets.BToolbar

/**
 * Created by Hanah on 11/22/2017.
 */
class LoginActivity : BaseBActivity() {
    private val bToolbar: BToolbar = toolbar

    override fun replaceFragmentId(): Int = R.id.fragmentContainer

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {

    }

    fun setTitle(string: String) {
        bToolbar.setToolbarTitle(string)
    }

    fun setLeftIcon(int: Int) {
        bToolbar.setLeftIcon(resources.getDrawable(int, theme))
    }

    fun setRightIcon(int: Int) {
        bToolbar.setRightIcon(resources.getDrawable(int, theme))
    }
}