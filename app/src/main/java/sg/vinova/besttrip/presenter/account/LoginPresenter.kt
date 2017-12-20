package sg.vinova.besttrip.presenter.account

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.account.LoginFragment
import javax.inject.Inject


/**
 * Created by hanah on 11/24/17.
 */
class LoginPresenter @Inject constructor(private var context: Context) : BaseBPresenter<LoginFragment>(context) {

    fun loginWithEmail(mAuth: FirebaseAuth, email: String, password: String) {
        requestSubscriptions.add(
                Observable.just("")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            mAuth.signInWithEmailAndPassword(email, password)
                                    .addOnSuccessListener({ weakReference.get()!!.loginSuccess() })
                                    .addOnFailureListener({ exception -> weakReference.get()!!.error(exception.localizedMessage) })
                        }, { throwable -> weakReference.get()!!.error(throwable.localizedMessage) })
        )
    }

    fun loginWithFacebook() {

    }
}
