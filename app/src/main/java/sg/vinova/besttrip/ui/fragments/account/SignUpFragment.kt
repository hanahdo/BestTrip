package sg.vinova.besttrip.ui.fragments.account

import android.text.TextUtils
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.jetbrains.anko.design.snackbar
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.presenter.account.SignUpPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.utils.KeyboardUtils
import sg.vinova.besttrip.utils.LogUtils
import sg.vinova.besttrip.widgets.dialogs.BErrorDialog
import javax.inject.Inject

/**
 * Created by hanah on 11/27/17.
 */
class SignUpFragment : BaseBFragment(), View.OnClickListener {
    private lateinit var mActivity: LoginActivity
    private lateinit var mAuth: FirebaseAuth
    @Inject lateinit var presenter: SignUpPresenter
    private var email: String = ""

    companion object {
        fun newInstance(): SignUpFragment = SignUpFragment()
        fun newInstance(email: String): SignUpFragment {
            val fragment = SignUpFragment()
            fragment.email = email
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_sign_up

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        if (!isAdded) return
        if (activity is LoginActivity)
            mActivity = activity as LoginActivity

        KeyboardUtils.setUpHideSoftKeyboard(mActivity, layoutContainer)

        mActivity.showToolbar()
        mActivity.bToolbar.setLeftIcon(R.drawable.drawer)

        mAuth = FirebaseAuth.getInstance()

        if (!TextUtils.isEmpty(email))
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
        if (v == null) return

        if (edtUsername.text != null || !TextUtils.isEmpty(edtUsername.text))
            username = edtUsername.text.toString()
        if (edtEmail.text != null || !TextUtils.isEmpty(edtEmail.text))
            email = edtEmail.text.toString()
        if (edtPassword.text != null || !TextUtils.isEmpty(edtPassword.text))
            password = edtPassword.text.toString()

        when (v.id) {
            R.id.tvLogin -> {
                if (!TextUtils.isEmpty(email))
                    changeFragment(LoginFragment.newInstance(email), false)
                changeFragment(LoginFragment.newInstance(), false)
            }
            R.id.btnSignUpEmail -> {
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                    presenter.signUpWithEmail(mAuth, username, email, password)
            }
        }
    }

    fun signUpSuccess() {
        LogUtils.bInfo(this.javaClass, "Login Success")
        snackbar(this.view!!, "Your have sign up successful with email: ${email}, now you can login!!")
        if (!TextUtils.isEmpty(email))
            changeFragment(LoginFragment.newInstance(email), false)
        changeFragment(LoginFragment.newInstance(), false)
    }

    fun error(error: String?) {
        LogUtils.bError(this.javaClass, error!!)
        BErrorDialog(context).setMessage(error)!!.show()
    }
}
