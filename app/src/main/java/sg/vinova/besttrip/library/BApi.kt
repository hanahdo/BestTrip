package sg.vinova.besttrip.library

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import sg.vinova.besttrip.model.autocomplete.BaseAutoComplete
import sg.vinova.besttrip.model.geocode.BaseGeocode

/**
 * Created by Hanah on 11/27/2017.
 */
interface BApi {
    companion object {
        const val place = "place/"
        const val autocomplete = "autocomplete/"
        const val geocode = "geocode/"
        const val directions = "directions/"
        var ApiKey = "AIzaSyBvv2dY3Eiq_eBGxLdO6OLvFQnR1yYYG6E"
    }

    @GET(place + autocomplete + "json")
    fun getListLocation(@Query("input") query: String, @Query("key") key: String = ApiKey): Flowable<BaseAutoComplete>

    @GET(geocode + "json")
    fun getAddressByLocation(@Query("latlng") latlng: String, @Query("key") key: String = ApiKey): Flowable<BaseGeocode>
}
