package sg.vinova.besttrip.ui.fragments

import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.presenter.LoginPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import javax.inject.Inject

/**
 * Created by hanah on 11/24/17.
 */
class LoginFragment : BaseBFragment() {
    private lateinit var mActivity: LoginActivity

    @Inject lateinit var presenter: LoginPresenter

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        if (!isAdded) return
        if (activity is LoginActivity)
            mActivity = activity as LoginActivity
    }

    override fun bindPresenter() {
        presenter.bind(this)
    }

    override fun unbindPresenter() {
        presenter.unbind()
    }
}