package sg.vinova.besttrip.presenter

import com.google.firebase.auth.FirebaseAuth
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.SplashFragment
import javax.inject.Inject


class SplashPresenter @Inject constructor() : BaseBPresenter<SplashFragment>() {
    fun checkUserLogin(mAuth: FirebaseAuth) {
        if (mAuth.currentUser != null) weakReference.get()!!.loginSuccess()
        else weakReference.get()!!.changeLoginFragment()
    }
}
