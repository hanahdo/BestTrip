package sg.vinova.besttrip.presenter

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.SplashFragment
import sg.vinova.besttrip.utils.SharedPreferencesUtils
import java.util.concurrent.TimeUnit

/**
 * Created by Hanah on 11/22/2017.
 */
class SplashPresenter(private var context: Context) : BaseBPresenter<SplashFragment>(context) {
    var token: String? = SharedPreferencesUtils.newInstance(context).getToken()
    fun checkUserLogin() {
        if (token == null) return
        Flowable.just(token)
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .map { t: String -> {
                    //Login to firebase
                } }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { s ->  }, Consumer<Throwable> { ex -> {weakReference.get().error(ex.localizedMessage)} })
        requestSubscriptions.add()
    }
}