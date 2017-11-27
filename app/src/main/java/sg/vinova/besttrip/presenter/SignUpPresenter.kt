package sg.vinova.besttrip.presenter

import android.content.Context
import com.google.firebase.auth.UserProfileChangeRequest
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.SignUpFragment
import sg.vinova.besttrip.utils.FirebaseUtils
import javax.inject.Inject

/**
 * Created by hanah on 11/27/17.
 */
class SignUpPresenter @Inject constructor(private var context: Context) : BaseBPresenter<SignUpFragment>(context) {

    fun signUpWithEmail(username: String, email: String, password: String) {
        requestSubscriptions!!.add(
                Flowable.just("")
                        .subscribeOn(Schedulers.computation())
                        .map {
                            FirebaseUtils.newInstance(context)
                                    .signUpWithEmail(email, password)
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ task ->
                            if (task.isComplete) {
                                task.result.user.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(username).build())
                                weakReference!!.get()!!.signUpSuccess()
                            } else
                                task.addOnFailureListener({ exception -> weakReference!!.get()!!.error(exception.localizedMessage) })
                        }, { throwable -> weakReference!!.get()!!.error(throwable.localizedMessage) })
        )
    }
}
