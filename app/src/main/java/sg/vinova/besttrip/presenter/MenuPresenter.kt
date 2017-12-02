package sg.vinova.besttrip.presenter

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.MenuFragment
import sg.vinova.besttrip.utils.FirebaseUtils
import javax.inject.Inject

/**
 * Created by Hanah on 11/23/2017.
 */
class MenuPresenter @Inject constructor(context: Context) : BaseBPresenter<MenuFragment>(context) {

    fun logout() {
        requestSubscriptions!!.add(Flowable.just("")
                .subscribeOn(Schedulers.single())
                .map { FirebaseUtils.logout() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ weakReference!!.get()!!.logoutSuccess() }, {t -> weakReference!!.get()!!.error(t.localizedMessage) }))
    }

}