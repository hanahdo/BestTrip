package sg.vinova.besttrip.ui.fragments

import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.fragment_splash.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.presenter.SplashPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.ui.fragments.account.LoginFragment
import sg.vinova.besttrip.utils.KeyboardUtils
import sg.vinova.besttrip.utils.LogUtils
import sg.vinova.besttrip.widgets.dialogs.BErrorDialog
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun init() {
        if (!isAdded) return
        if (activity is LoginActivity)
            mActivity = activity as LoginActivity

        KeyboardUtils.setUpHideSoftKeyboard(mActivity, layoutContainer)

        mActivity.hideToolbar()

        Handler().postDelayed({ presenter.checkUserLogin() }, 2000)
    }

    override fun bindPresenter() = presenter.bind(this)

    override fun unbindPresenter() = presenter.unbind()

    fun error(error: String?) {
        LogUtils.bError(error!!)
        BErrorDialog(context).setMessage(error)!!.show()
    }

    fun loginSuccess() {
        LogUtils.bInfo("Login Success")
        changeActivity(MapActivity::class.java)
    }

    fun changeLoginFragment() {
        changeFragment(LoginFragment.newInstance(), false)
    }
}
