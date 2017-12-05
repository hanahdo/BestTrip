package sg.vinova.besttrip.presenter

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.SplashFragment
import javax.inject.Inject

/**
 * Created by Hanah on 11/22/2017.
 */
class SplashPresenter @Inject constructor(private var context: Context) : BaseBPresenter<SplashFragment>(context) {
    fun checkUserLogin(mAuth: FirebaseAuth) {
        requestSubscriptions!!.add(
                Observable.just("")
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (mAuth.currentUser != null) weakReference!!.get()!!.loginSuccess()
                            else weakReference!!.get()!!.changeLoginFragment()
                        }, { throwable -> weakReference!!.get()!!.error(throwable.localizedMessage) })
        )
    }
}
