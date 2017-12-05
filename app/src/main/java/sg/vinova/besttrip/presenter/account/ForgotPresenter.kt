package sg.vinova.besttrip.presenter.account

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.account.ForgotFragment
import javax.inject.Inject

/**
 * Created by hanah on 11/27/17.
 */
class ForgotPresenter @Inject constructor(private var context: Context) : BaseBPresenter<ForgotFragment>(context) {
    fun forgotPassword(mAuth: FirebaseAuth, email: String) {
        requestSubscriptions!!.add(
                Observable.just("")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            mAuth.sendPasswordResetEmail(email)
                                    .addOnSuccessListener({ weakReference!!.get()!!.forgotSuccess() })
                                    .addOnFailureListener({ exception -> weakReference!!.get()!!.error(exception.localizedMessage) })
                        }, { throwable -> weakReference!!.get()!!.error(throwable.localizedMessage) })
        )
    }
}
