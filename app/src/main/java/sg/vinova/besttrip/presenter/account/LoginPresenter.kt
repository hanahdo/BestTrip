package sg.vinova.besttrip.presenter.account

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.account.LoginFragment
import sg.vinova.besttrip.utils.FirebaseUtils
import javax.inject.Inject


/**
 * Created by hanah on 11/24/17.
 */
class LoginPresenter @Inject constructor(private var context: Context) : BaseBPresenter<LoginFragment>(context) {

    fun loginWithEmail(email: String, password: String) {
        requestSubscriptions!!.add(
                Flowable.just("")
                        .subscribeOn(Schedulers.computation())
                        .map {
                            FirebaseUtils.loginWithEmail(email, password)
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ authTask ->
                            authTask.addOnCompleteListener({ task -> weakReference!!.get()!!.loginSuccess() })
                            authTask.addOnFailureListener({ exception -> weakReference!!.get()!!.error(exception.localizedMessage) })
                        }, { throwable -> weakReference!!.get()!!.error(throwable.localizedMessage) })
        )
    }

    fun loginWithFacebook() {

    }
}
