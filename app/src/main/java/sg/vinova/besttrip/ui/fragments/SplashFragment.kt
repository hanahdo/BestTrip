package sg.vinova.besttrip.ui.fragments

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBFragment

/**
 * Created by Hanah on 11/22/2017.
 */
class SplashFragment : BaseBFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun inject() {
        BApplication.instance.component.inject(this)
    }

    override fun init() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindPresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unbindPresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun error(localizedMessage: String?) {
        AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(localizedMessage)
                .create().apply {
            setButton(DialogInterface.BUTTON_NEUTRAL,
                    "OK",
                    { iDialog, which: Int -> iDialog.dismiss() })
            show()
            setCanceledOnTouchOutside(true)
        }
    }
}