package sg.vinova.besttrip.usecase

import android.content.Context
import io.reactivex.Flowable
import sg.vinova.besttrip.base.BaseUseCase
import sg.vinova.besttrip.base.DataManager
import sg.vinova.besttrip.model.autocomplete.BaseAutoComplete
import sg.vinova.besttrip.model.places.BaseObjectResponse
import javax.inject.Inject

/**
 * Created by Hanah on 11/27/2017.
 */
class SearchUsecase @Inject constructor(context: Context, manager: DataManager) : BaseUseCase<Void>(context, manager) {

    fun getAllSearchResult(input: String): Flowable<BaseAutoComplete> {
        return manager.getApi().getListLocation(input = input)
    }

    fun getAddress(latlng: String): Flowable<BaseObjectResponse> {
        return manager.getApi().getAddressByLocation(latlng = latlng)
    }

    fun getNearbyList(latlng: String): Flowable<BaseObjectResponse> {
        return manager.getApi().getNearbyList(location = latlng)
    }

    fun getLocationByPlaceId(placeId: String): Flowable<BaseObjectResponse> {
        return manager.getApi().getLocationByPlaceId(placeId = placeId)
    }
}