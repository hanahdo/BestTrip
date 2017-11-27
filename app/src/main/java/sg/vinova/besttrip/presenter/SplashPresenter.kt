package sg.vinova.besttrip.presenter

import android.content.Context
import android.text.TextUtils
import io.reactivex.functions.BiConsumer
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.SplashFragment
import sg.vinova.besttrip.utils.FirebaseUtils
import sg.vinova.besttrip.utils.SharedPreferencesUtils
import javax.inject.Inject

/**
 * Created by Hanah on 11/22/2017.
 */
class SplashPresenter @Inject constructor(private var context: Context) : BaseBPresenter<SplashFragment>(context) {
    var token: String? = SharedPreferencesUtils.newInstance(context).getToken()

    fun checkUserLogin() {
        if (token == null || TextUtils.isEmpty(token)) weakReference.get()!!.changeLoginFragment()
        requestSubscriptions.add(FirebaseUtils.newInstance(context)
                .rxLoginWithCustomToken(token!!)
                .subscribe({ mUser, throwable -> if (throwable != null) weakReference.get()!!.error(throwable.localizedMessage) else weakReference.get()!!.loginSuccess(mUser) }))


    }
}