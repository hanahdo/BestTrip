package sg.vinova.besttrip.presenter

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.SplashFragment
import sg.vinova.besttrip.utils.FirebaseUtils
import javax.inject.Inject

/**
 * Created by Hanah on 11/22/2017.
 */
class SplashPresenter @Inject constructor(private var context: Context) : BaseBPresenter<SplashFragment>(context) {
    fun checkUserLogin() {
        requestSubscriptions!!.add(
                Flowable.just(" ")
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            val mUser = FirebaseUtils.getCurrentUser()
                            if (mUser != null) weakReference!!.get()!!.loginSuccess()
                            else weakReference!!.get()!!.changeLoginFragment()
                        }, { throwable -> weakReference!!.get()!!.error(throwable.localizedMessage) })
        )
    }
}
