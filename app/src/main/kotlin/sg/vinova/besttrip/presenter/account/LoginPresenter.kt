package sg.vinova.besttrip.presenter.account

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.exts.errorWithDialog
import sg.vinova.besttrip.ui.fragments.account.LoginFragment
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val context: Context) : BaseBPresenter<LoginFragment>() {

    fun loginWithEmail(mAuth: FirebaseAuth, email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener({ weakReference.get()!!.loginSuccess() })
                .addOnFailureListener({ exception -> weakReference.get()!!.errorWithDialog(context, exception.localizedMessage) })
    }

    fun loginWithFacebook() {

    }
}
