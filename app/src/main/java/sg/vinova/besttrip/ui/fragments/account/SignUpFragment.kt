package sg.vinova.besttrip.ui.fragments.account

import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.view_login_signup.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.exts.checkIsAdded
import sg.vinova.besttrip.exts.error
import sg.vinova.besttrip.exts.info
import sg.vinova.besttrip.exts.setUpHideSoftKeyboard
import sg.vinova.besttrip.presenter.account.SignUpPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.widgets.dialogs.BErrorDialog
import javax.inject.Inject

class SignUpFragment : BaseBFragment(), View.OnClickListener {
    private lateinit var mActivity: LoginActivity
    private lateinit var mAuth: FirebaseAuth
    @Inject
    lateinit var presenter: SignUpPresenter
    private var email: String = ""

    companion object {
        fun newInstance(email: String): SignUpFragment {
            val fragment = SignUpFragment()
            fragment.email = email
            return fragment
        }
    }

    override fun getLeftIcon(): Int = R.drawable.drawer

    override fun getLayoutId(): Int = R.layout.fragment_sign_up

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
        tvLogin.setOnClickListener(this)
        btnSignUpEmail.setOnClickListener(this)
    }


    override fun bindPresenter() {
        presenter.bind(this)
    }

    override fun unbindPresenter() {
        presenter.unbind()
    }

    private var password: String = ""
    private var username: String = ""

    override fun onClick(v: View?) {
        username = edtUsername.text.toString()
        email = edtEmail.text.toString()
        password = edtPassword.text.toString()

        when (v?.id) {
            R.id.tvLogin -> changeFragment(LoginFragment.newInstance(email), false)
            R.id.btnSignUpEmail -> getDataLogin()
        }
    }

    private fun getDataLogin() {
        if (username.isEmpty()) {
            edtEmail.error = getString(R.string.username_is_empty)
            return
        }

        if (email.isEmpty()) {
            edtEmail.error = getString(R.string.email_is_empty)
            return
        }

        if (password.isEmpty()) {
            edtPassword.error = getString(R.string.password_is_empty)
            return
        }

        presenter.signUpWithEmail(mAuth, username, email, password)
    }

    fun signUpSuccess() {
        javaClass.info("Login Success")
        Toast.makeText(context, "Your have sign up successful with email: $email, now you can login!!", Toast.LENGTH_SHORT).show()
        changeFragment(LoginFragment.newInstance(email), false)
    }

    fun error(error: String?) {
        javaClass.error(error!!)
        BErrorDialog(context).setMessage(error)!!.show()
    }
}
