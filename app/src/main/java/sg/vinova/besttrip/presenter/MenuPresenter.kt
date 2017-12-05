package sg.vinova.besttrip.presenter

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.MenuFragment
import javax.inject.Inject

/**
 * Created by Hanah on 11/23/2017.
 */
class MenuPresenter @Inject constructor(context: Context) : BaseBPresenter<MenuFragment>(context) {

    fun logout(mAuth: FirebaseAuth) {
        requestSubscriptions!!.add(
                Observable.just("")
                        .subscribeOn(Schedulers.single())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            mAuth.signOut()
                            weakReference!!.get()!!.logoutSuccess()
                        }, { throwable -> weakReference!!.get()!!.error(throwable.localizedMessage) }))
    }

}
