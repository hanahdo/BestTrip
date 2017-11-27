package sg.vinova.besttrip.ui.fragments

import android.os.Handler
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.presenter.SplashPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.widgets.dialogs.BDialog
import javax.inject.Inject

/**
 * Created by Hanah on 11/22/2017.
 */
class SplashFragment : BaseBFragment() {

    @Inject lateinit var presenter: SplashPresenter
    private lateinit var mActivity: LoginActivity

    companion object {
        fun newInstance(): SplashFragment = SplashFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        if (!isAdded) return
        if (activity is LoginActivity)
            mActivity = activity as LoginActivity

        mActivity.hideToolbar()

        Handler().postDelayed({ presenter.checkUserLogin() }, 2000)
    }

    override fun bindPresenter() = presenter.bind(this)

    override fun unbindPresenter() = presenter.unbind()

    fun error(error: String?) {
        BDialog(context).setMessage(error)!!.show()
    }

    fun loginSuccess() {
        changeActivity(MapActivity::class.java)
    }

    fun changeLoginFragment() {
        changeFragment(LoginFragment.newInstance(), false)
    }
}
