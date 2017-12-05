package sg.vinova.besttrip.ui.fragments.account

import android.text.TextUtils
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_forgot.*
import org.jetbrains.anko.design.snackbar
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.presenter.account.ForgotPresenter
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.utils.KeyboardUtils
import sg.vinova.besttrip.utils.LogUtils
import sg.vinova.besttrip.widgets.dialogs.BErrorDialog
import javax.inject.Inject

/**
 * Created by hanah on 11/27/17.
 */
class ForgotFragment : BaseBFragment(), View.OnClickListener, BaseListener.OnToolbarClickListener {
    private lateinit var mActivity: LoginActivity
    @Inject lateinit var presenter: ForgotPresenter
    private lateinit var mAuth: FirebaseAuth

    companion object {
        fun newInstance(): ForgotFragment = ForgotFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_forgot

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        if (!isAdded) return
        if (activity is LoginActivity)
            mActivity = activity as LoginActivity

        KeyboardUtils.setUpHideSoftKeyboard(mActivity, layoutContainer)

        mActivity.showToolbar()
        mActivity.bToolbar.setLeftIcon(R.drawable.back)

        mAuth = FirebaseAuth.getInstance()

        onClick()
    }

    private fun onClick() {
        mActivity.bToolbar.listener = this
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
        if (v == null) return
        if (!TextUtils.isEmpty(edtEmail.text) || edtEmail.text != null)
            email = edtEmail.text.toString()

        when (v.id) {
            R.id.btnSend -> {
                LogUtils.bDebug(this.javaClass, "Send click, email: $email")
                if (!TextUtils.isEmpty(email)) presenter.forgotPassword(mAuth, email)
            }
        }
    }

    override fun onLeftClick() {
        LogUtils.bDebug(this.javaClass, "On Left Click")
        mActivity.onBackPressed()
    }

    override fun onRightClick() {
        LogUtils.bDebug(this.javaClass, "On Right Click")
    }

    fun forgotSuccess() {
        snackbar(this.view!!, "Please check your mailbox to reset your password.")
        LogUtils.bInfo(this.javaClass, "An email reset password has sent to ${email}!")
        if (!TextUtils.isEmpty(email))
            changeFragment(LoginFragment.newInstance(email), false)
        changeFragment(LoginFragment.newInstance(), false)
    }

    fun error(error: String?) {
        LogUtils.bError(this.javaClass, error!!)
        BErrorDialog(context).setMessage(error)!!.show()
    }
}
