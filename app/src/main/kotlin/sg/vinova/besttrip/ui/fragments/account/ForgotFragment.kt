package sg.vinova.besttrip.ui.fragments.account

import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_forgot.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.exts.*
import sg.vinova.besttrip.presenter.account.ForgotPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.widgets.dialogs.BErrorDialog
import javax.inject.Inject

class ForgotFragment : BaseBFragment(), View.OnClickListener {
    private lateinit var mActivity: LoginActivity
    @Inject
    lateinit var presenter: ForgotPresenter
    private lateinit var mAuth: FirebaseAuth

    override fun getLeftIcon(): Int = R.drawable.back

    companion object {
        fun newInstance(): ForgotFragment = ForgotFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_forgot

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        checkIsAdded()
        if (activity is LoginActivity)
            mActivity = activity as LoginActivity

        mActivity.apply {
            setUpHideSoftKeyboard(layoutContainer)
        }

        mAuth = FirebaseAuth.getInstance()

        onClick()
    }

    private fun onClick() {
        btnSend.setOnClickListener(this)
    }

    override fun bindPresenter() {
        presenter.bind(this)
    }

    override fun unbindPresenter() {
        presenter.unbind()
    }

    private var email: String = ""

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSend -> {
                if (edtEmail.text.isNotEmpty()) {
                    email = edtEmail.text.toString()
                    presenter.forgotPassword(mAuth, email)
                    javaClass.debug("Send click, email: $email")
                } else edtEmail.error = getString(R.string.email_is_empty)
            }
        }
    }

    fun forgotSuccess() {
        Toast.makeText(context, "Please check your mailbox to reset your password.", Toast.LENGTH_SHORT).show()
        info("An email reset password has sent to $email!")
        changeFragment(LoginFragment.newInstance(email))
    }
}
