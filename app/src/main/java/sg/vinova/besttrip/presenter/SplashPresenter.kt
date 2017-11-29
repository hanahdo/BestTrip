package sg.vinova.besttrip.presenter

import android.content.Context
import android.text.TextUtils
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.SplashFragment
import sg.vinova.besttrip.utils.FirebaseUtils
import sg.vinova.besttrip.utils.SharedPreferencesUtils
import javax.inject.Inject

/**
 * Created by Hanah on 11/22/2017.
 */
class SplashPresenter @Inject constructor(private var context: Context) : BaseBPresenter<SplashFragment>(context) {
    private var token: String? = SharedPreferencesUtils.newInstance(context).getToken()

    fun checkUserLogin() {
        if (token == null || TextUtils.isEmpty(token)) weakReference!!.get()!!.changeLoginFragment()
        requestSubscriptions!!.add(
                Flowable.just("")
                        .subscribeOn(Schedulers.computation())
                        .map {
                            FirebaseUtils.loginWithCustomToken(token!!)
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ authTask ->
                            authTask.addOnCompleteListener({ weakReference!!.get()!!.loginSuccess() })
                            authTask.addOnFailureListener({ exception -> weakReference!!.get()!!.error(exception.localizedMessage) })
                        }, { throwable -> weakReference!!.get()!!.error(throwable.localizedMessage) })
        )
    }
}
