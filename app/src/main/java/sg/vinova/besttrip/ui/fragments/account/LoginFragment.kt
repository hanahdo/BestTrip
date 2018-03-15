package sg.vinova.besttrip.ui.fragments.account

import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.view_login_signup.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.exts.checkIsAdded
import sg.vinova.besttrip.exts.error
import sg.vinova.besttrip.exts.info
import sg.vinova.besttrip.exts.setUpHideSoftKeyboard
import sg.vinova.besttrip.presenter.account.LoginPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.widgets.dialogs.BErrorDialog
import javax.inject.Inject

/**
 * Created by hanah on 11/24/17.
 */
class LoginFragment : BaseBFragment(), View.OnClickListener {
    private lateinit var mActivity: LoginActivity
    private lateinit var mAuth: FirebaseAuth

    @Inject
    lateinit var presenter: LoginPresenter

    override fun getLeftIcon(): Int = R.drawable.drawer

    companion object {
        fun newInstance(email: String): LoginFragment {
            val fragment = LoginFragment()
            fragment.email = email
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        checkIsAdded()

        if (activity is LoginActivity)
            mActivity = activity as LoginActivity

        mActivity.apply {
            setUpHideSoftKeyboard(layoutContainer)
            showToolbar()
        }

        mAuth = FirebaseAuth.getInstance()

        if (email.isNotEmpty())
            edtEmail.setText(email)

        onClick()
    }

    private fun onClick() {
        tvSignUp.setOnClickListener(this)
        btnLoginEmail.setOnClickListener(this)
        tvForgot.setOnClickListener(this)
        tvSkip.setOnClickListener(this)
    }

    override fun bindPresenter() {
        presenter.bind(this)
    }

    override fun unbindPresenter() {
        presenter.unbind()
    }

    private var email: String = ""
    private var password: String = ""

    override fun onClick(v: View?) {
        email = edtEmail.text.toString()
        password = edtPassword.text.toString()
        when (v?.id) {
            R.id.tvSignUp -> changeFragment(SignUpFragment.newInstance(email), false)
            R.id.btnLoginEmail -> getDataLogin()
            R.id.tvForgot -> changeFragment(ForgotFragment.newInstance(), true)
            R.id.tvSkip -> changeActivity(MapActivity::class.java)
        }
    }

    private fun getDataLogin() {
        if (email.isEmpty()) {
            edtEmail.error = getString(R.string.email_is_empty)
            return
        }

        if (password.isEmpty()) {
            edtPassword.error = getString(R.string.password_is_empty)
            return
        }

        presenter.loginWithEmail(mAuth, email, password)
    }

    fun loginSuccess() {
        javaClass.info("Login Success with email: $email")
        changeActivity(MapActivity::class.java)
    }

    fun error(error: String?) {
        javaClass.error(error!!)
        BErrorDialog(context).setMessage(error)!!.show()
    }
}
