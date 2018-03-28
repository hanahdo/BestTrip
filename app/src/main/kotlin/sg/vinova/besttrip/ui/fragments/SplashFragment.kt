package sg.vinova.besttrip.ui.fragments

import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_splash.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.exts.checkIsAdded
import sg.vinova.besttrip.exts.info
import sg.vinova.besttrip.exts.setUpHideSoftKeyboard
import sg.vinova.besttrip.presenter.SplashPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.ui.fragments.account.LoginFragment
import javax.inject.Inject


class SplashFragment : BaseBFragment() {
    override fun getLeftIcon(): Int = 0

    @Inject
    lateinit var presenter: SplashPresenter
    private lateinit var mActivity: LoginActivity
    private lateinit var mAuth: FirebaseAuth

    companion object {
        fun newInstance(): SplashFragment = SplashFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        checkIsAdded()
        if (activity is LoginActivity)
            mActivity = activity as LoginActivity

        mActivity.setUpHideSoftKeyboard(layoutContainer)

        mAuth = FirebaseAuth.getInstance()

        Handler().postDelayed({ presenter.checkUserLogin(mAuth) }, 2000)
    }

    override fun bindPresenter() = presenter.bind(this)

    override fun unbindPresenter() = presenter.unbind()

    fun loginSuccess() {
        info("Login Success")
        changeActivity(MapActivity::class.java)
    }

    fun changeLoginFragment() {
        changeFragment(LoginFragment.newInstance(""))
    }
}
