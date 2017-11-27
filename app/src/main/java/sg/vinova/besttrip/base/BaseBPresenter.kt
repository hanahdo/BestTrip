package sg.vinova.besttrip.base

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by Hanah on 11/22/2017.
 */
open class BaseBPresenter<T>(context: Context) {
    var weakReference: WeakReference<T>? = null
    var requestSubscriptions: CompositeDisposable? = null

    fun isViewExisted(): Boolean = weakReference!!.get() != null

    fun bind(t: T) {
        weakReference = WeakReference(t)
        requestSubscriptions = CompositeDisposable()
    }

    fun unbind() {
        if (requestSubscriptions == null) return
        requestSubscriptions!!.clear()
        requestSubscriptions!!.dispose()
    }
}
