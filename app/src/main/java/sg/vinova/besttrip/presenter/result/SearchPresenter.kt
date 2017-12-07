package sg.vinova.besttrip.presenter.result

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sg.vinova.besttrip.base.BaseBPresenter
import sg.vinova.besttrip.model.places.Location
import sg.vinova.besttrip.ui.fragments.result.SearchFragment
import sg.vinova.besttrip.usecase.SearchUsecase
import javax.inject.Inject

/**
 * Created by Hanah on 12/7/2017.
 */
class SearchPresenter @Inject constructor(private var context: Context) : BaseBPresenter<SearchFragment>(context) {
    @Inject lateinit var searchUsecase: SearchUsecase

    fun getNearby(location: Location) {
        requestSubscriptions!!.add(searchUsecase.getNearbyList("${location.lat},${location.lng}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ baseObjResponse ->
                    if (baseObjResponse.status == "OK")
                        weakReference!!.get()!!.getNearbySuccess(baseObjResponse.results)
                    else
                        weakReference!!.get()!!.error(baseObjResponse.status)
                }, { t -> weakReference!!.get()!!.error(t.localizedMessage) }))
    }

    fun getSearchResult(input: String) {
        requestSubscriptions!!.add(searchUsecase.getAllSearchResult(input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ baseAutoComplete ->
                    if (baseAutoComplete.status == "OK")
                        weakReference!!.get()!!.getSearchResultSuccess(baseAutoComplete.predictions)
                }, { t -> weakReference!!.get()!!.error(t.localizedMessage) }))
    }
}