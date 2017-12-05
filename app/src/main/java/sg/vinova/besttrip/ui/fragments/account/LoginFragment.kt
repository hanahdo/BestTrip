package sg.vinova.besttrip.ui.fragments.account

import android.text.TextUtils
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.presenter.account.LoginPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.ui.activities.MapActivity
import sg.vinova.besttrip.utils.KeyboardUtils
import sg.vinova.besttrip.utils.LogUtils
import sg.vinova.besttrip.utils.SharedPreferencesUtils
import sg.vinova.besttrip.widgets.dialogs.BErrorDialog
import javax.inject.Inject

/**
 * Created by hanah on 11/24/17.
 */
class LoginFragment : BaseBFragment(), View.OnClickListener {
    private lateinit var mActivity: LoginActivity
    private lateinit var mAuth: FirebaseAuth

    @Inject lateinit var presenter: LoginPresenter

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
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
        if (!isAdded) return

        if (activity is LoginActivity)
            mActivity = activity as LoginActivity

        KeyboardUtils.setUpHideSoftKeyboard(mActivity, layoutContainer)

        mActivity.showToolbar()

        mActivity.setLeftIcon(R.drawable.drawer)

        mAuth = FirebaseAuth.getInstance()

        if (!TextUtils.isEmpty(email))
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
        if (v == null) return
        if (edtEmail.text != null || !TextUtils.isEmpty(edtEmail.text))
            email = edtEmail.text.toString()
        if (edtPassword.text != null || !TextUtils.isEmpty(edtPassword.text))
            password = edtPassword.text.toString()
        when (v.id) {
            R.id.tvSignUp -> {
                if (!TextUtils.isEmpty(email))
                    changeFragment(SignUpFragment.newInstance(email), false)
                changeFragment(SignUpFragment.newInstance(), false)
            }
            R.id.btnLoginEmail -> {
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                    presenter.loginWithEmail(mAuth, email, password)
            }
            R.id.tvForgot -> {
                changeFragment(ForgotFragment.newInstance(), true)
            }
            R.id.tvSkip -> {
                changeActivity(MapActivity::class.java)
            }
        }
    }

    fun loginSuccess() {
        LogUtils.bInfo(this.javaClass, "Login Success with email: $email")
        changeActivity(MapActivity::class.java)
    }

    fun error(error: String?) {
        LogUtils.bError(this.javaClass, error!!)
        BErrorDialog(context).setMessage(error)!!.show()
    }
}
