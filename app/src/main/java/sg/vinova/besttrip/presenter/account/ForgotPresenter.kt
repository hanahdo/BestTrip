package sg.vinova.besttrip.presenter.account

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.account.ForgotFragment
import sg.vinova.besttrip.utils.FirebaseUtils
import javax.inject.Inject

/**
 * Created by hanah on 11/27/17.
 */
class ForgotPresenter @Inject constructor(private var context: Context) : BaseBPresenter<ForgotFragment>(context) {
    fun forgotPassword(email: String) {
        requestSubscriptions!!.add(
                Flowable.just("")
                        .subscribeOn(Schedulers.computation())
                        .map {
                            FirebaseUtils.forgotPassword(email)
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ authTask ->
                            authTask.addOnCompleteListener({ weakReference!!.get()!!.forgotSuccess() })
                            authTask.addOnFailureListener({ exception -> weakReference!!.get()!!.error(exception.localizedMessage) })
                        }, { throwable -> weakReference!!.get()!!.error(throwable.localizedMessage) })
        )
    }
}
