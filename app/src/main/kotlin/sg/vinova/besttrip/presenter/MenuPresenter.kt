package sg.vinova.besttrip.presenter

import com.google.firebase.auth.FirebaseAuth
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.MenuFragment
import javax.inject.Inject

class MenuPresenter @Inject constructor() : BaseBPresenter<MenuFragment>() {

    fun logout(mAuth: FirebaseAuth) {
        mAuth.signOut()
        if (mAuth.currentUser == null)
            weakReference.get()!!.logoutSuccess()
    }
}
