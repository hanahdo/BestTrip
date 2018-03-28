package sg.vinova.besttrip.presenter.account

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.exts.errorWithDialog
import sg.vinova.besttrip.ui.fragments.account.ForgotFragment
import javax.inject.Inject

class ForgotPresenter @Inject constructor(private val context: Context) : BaseBPresenter<ForgotFragment>() {
    fun forgotPassword(mAuth: FirebaseAuth, email: String) {
        mAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener({ weakReference.get()!!.forgotSuccess() })
                .addOnFailureListener({ exception -> weakReference.get()!!.errorWithDialog(context, exception.localizedMessage) })
    }
}
