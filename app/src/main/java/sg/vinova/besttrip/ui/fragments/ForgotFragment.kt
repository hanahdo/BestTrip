package sg.vinova.besttrip.ui.fragments

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.fragment_forgot.*
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.presenter.ForgotPresenter
import sg.vinova.besttrip.services.BaseListener
import javax.inject.Inject

/**
 * Created by hanah on 11/27/17.
 */
class ForgotFragment : BaseBFragment(), View.OnClickListener, BaseListener.OnToolbarClickListener {
    private lateinit var mActivity: LoginActivity
    @Inject lateinit var presenter: ForgotPresenter

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

        onClick()
    }

    private fun onClick() {
        mActivity.setToolbarListener(this)
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
            R.id.btnSend -> if (!TextUtils.isEmpty(email)) presenter.forgotPassword(email)
        }
    }

    override fun onLeftClick() {
        mActivity.onBackPressed()
    }

    override fun onRightClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun forgotSuccess() {
        if (!TextUtils.isEmpty(email))
            changeFragment(LoginFragment.newInstance(email), false)
        changeFragment(LoginFragment.newInstance(), false)
    }

    fun error(localizedMessage: String?) {
        AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(localizedMessage)
                .create().apply {
            setButton(DialogInterface.BUTTON_NEUTRAL,
                    "OK",
                    { iDialog, _ -> iDialog.dismiss() })
            show()
            setCanceledOnTouchOutside(true)
        }
    }
}
