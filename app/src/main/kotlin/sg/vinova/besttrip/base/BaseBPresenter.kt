package sg.vinova.besttrip.base

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

open class BaseBPresenter<T> {
    lateinit var weakReference: WeakReference<T>
    lateinit var requestSubscriptions: CompositeDisposable

    fun isViewExisted(): Boolean = weakReference.get() != null

    fun bind(t: T) {
        weakReference = WeakReference(t)
        requestSubscriptions = CompositeDisposable()
    }

    fun unbind() {
        requestSubscriptions.clear()
        requestSubscriptions.dispose()
    }
}
