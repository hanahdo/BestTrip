package sg.vinova.besttrip.presenter.account

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.exts.errorWithDialog
import sg.vinova.besttrip.ui.fragments.account.SignUpFragment
import javax.inject.Inject

class SignUpPresenter @Inject constructor(private val context: Context) : BaseBPresenter<SignUpFragment>() {

    fun signUpWithEmail(mAuth: FirebaseAuth, username: String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener({ result ->
                    result.user.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(username).build())
                    weakReference.get()!!.signUpSuccess()
                })
                .addOnFailureListener({ exception -> weakReference.get()!!.errorWithDialog(context, exception.localizedMessage) })
    }
}
