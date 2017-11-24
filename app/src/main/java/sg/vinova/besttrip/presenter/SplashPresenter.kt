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
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Hanah on 11/22/2017.
 */
class SplashPresenter @Inject constructor(private var context: Context) : BaseBPresenter<SplashFragment>(context) {
    var token: String? = SharedPreferencesUtils.newInstance(context).getToken()

    fun checkUserLogin() {
        if (token == null || TextUtils.isEmpty(token)) weakReference.get()!!.changeLoginFragment()
        requestSubscriptions.add(Flowable.just("").delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .map { FirebaseUtils.newInstance(context).loginWithToken(token!!) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { weakReference.get()!!.loginSuccess() },
                        { ex -> weakReference.get()!!.error(ex.localizedMessage) }))
    }
}