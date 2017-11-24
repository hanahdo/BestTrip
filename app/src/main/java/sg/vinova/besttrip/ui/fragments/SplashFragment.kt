package sg.vinova.besttrip.ui.fragments

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment
import sg.vinova.besttrip.presenter.SplashPresenter
import sg.vinova.besttrip.ui.activities.LoginActivity
import sg.vinova.besttrip.ui.activities.MapActivity
import javax.inject.Inject

/**
 * Created by Hanah on 11/22/2017.
 */
class SplashFragment : BaseBFragment() {

    @Inject lateinit var presenter: SplashPresenter
    private lateinit var mActivity: LoginActivity

    companion object {
        fun newInstance(): SplashFragment = SplashFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        if (!isAdded) return
        if (activity is LoginActivity)
            mActivity = activity as LoginActivity
    }

    override fun bindPresenter() = presenter.bind(this)

    override fun unbindPresenter() = presenter.unbind()

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

    fun loginSuccess() {
        changeActivity(MapActivity::class.java)
    }

    fun changeLoginFragment() {
//        changeFragment()
    }
}