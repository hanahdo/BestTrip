package sg.vinova.besttrip.presenter.result

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.exts.errorWithDialog
import sg.vinova.besttrip.ui.fragments.result.MapFragment
import sg.vinova.besttrip.usecase.SearchUsecase
import javax.inject.Inject

class MapPresenter @Inject constructor(private val context: Context) : BaseBPresenter<MapFragment>() {
    @Inject
    lateinit var searchUsecase: SearchUsecase

    fun getLocationList(s: String) {
        requestSubscriptions.add(searchUsecase.getAllSearchResult(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ baseObject ->
                    if (baseObject.status == "OK" || baseObject.predictions!!.isNotEmpty()) {
                        weakReference.get()!!.getSuccess(baseObject.predictions)
                    } else {
                        weakReference.get()!!.errorWithDialog(context, baseObject.status)
                    }
                }))
    }
}
