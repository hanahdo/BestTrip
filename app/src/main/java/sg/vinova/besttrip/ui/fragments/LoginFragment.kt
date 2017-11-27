package sg.vinova.besttrip.ui.fragments

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.fragment_login.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.presenter.LoginPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.ui.activities.MapActivity
import javax.inject.Inject

/**
 * Created by hanah on 11/24/17.
 */
class LoginFragment : BaseBFragment(), View.OnClickListener {
    private lateinit var mActivity: LoginActivity

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

        mActivity.showToolbar()

        mActivity.setLeftIcon(R.drawable.drawer)

        if (!TextUtils.isEmpty(email))
            edtEmail.setText(email)

        onClick()
    }

    private fun onClick() {
        tvSignUp.setOnClickListener(this)
        btnLoginEmail.setOnClickListener(this)
        tvForgot.setOnClickListener(this)
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
                    presenter.loginWithEmail(email, password)
            }
            R.id.tvForgot -> {
                changeFragment(ForgotFragment.newInstance(), true)
            }
        }
    }

    fun loginSuccess() {
        changeActivity(MapActivity::class.java)
    }

    fun error(error: String?) {
        AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(error)
                .create().apply {
            setButton(DialogInterface.BUTTON_NEUTRAL,
                    "OK",
                    { iDialog, _ -> iDialog.dismiss() })
            show()
            setCanceledOnTouchOutside(true)
        }
    }
}
