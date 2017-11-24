package sg.vinova.besttrip.presenter

import android.content.Context
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.LoginFragment
import javax.inject.Inject

/**
 * Created by hanah on 11/24/17.
 */
class LoginPresenter @Inject constructor(context: Context) : BaseBPresenter<LoginFragment>(context) {

    fun loginWithEmail(email: String, password: String) {

    }

    fun loginWithFacebook() {

    }
}