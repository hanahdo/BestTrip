package sg.vinova.besttrip.presenter

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.ui.fragments.MapFragment
import sg.vinova.besttrip.usecase.SearchUsecase
import javax.inject.Inject

/**
 * Created by Hanah on 11/27/2017.
 */
class MapPresenter @Inject constructor(private var context: Context) : BaseBPresenter<MapFragment>(context) {
    @Inject lateinit var searchUsecase: SearchUsecase
    fun getLocationList(s: String) {
        requestSubscriptions!!.add(searchUsecase.getAllSearchResult(s)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ baseObject ->
                    if (baseObject.status == "OK" || baseObject.results!!.isNotEmpty()){
                        weakReference!!.get()!!.getSuccess(baseObject.results)
                    } else{
                        weakReference!!.get()!!.error(baseObject.status)
                    }
                }))
    }
}