package sg.vinova.besttrip.presenter.account

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.account.SignUpFragment
import javax.inject.Inject

/**
 * Created by hanah on 11/27/17.
 */
class SignUpPresenter @Inject constructor(private var context: Context) : BaseBPresenter<SignUpFragment>(context) {

    fun signUpWithEmail(mAuth: FirebaseAuth, username: String, email: String, password: String) {
        requestSubscriptions!!.add(
                Observable.just("")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            mAuth.createUserWithEmailAndPassword(email, password)
                                    .addOnSuccessListener({ result ->
                                        result.user.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(username).build())
                                        weakReference!!.get()!!.signUpSuccess()
                                    })
                                    .addOnFailureListener({ exception -> weakReference!!.get()!!.error(exception.localizedMessage) })
                        }, { throwable -> weakReference!!.get()!!.error(throwable.localizedMessage) })
        )
    }
}
