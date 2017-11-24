package sg.vinova.besttrip.ui.activities

import kotlinx.android.synthetic.main.activity_login.view.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBActivity

/**
 * Created by Hanah on 11/23/2017.
 */
class MapActivity : BaseBActivity() {
    override fun replaceFragmentId(): Int = R.id.fragmentContainer

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}