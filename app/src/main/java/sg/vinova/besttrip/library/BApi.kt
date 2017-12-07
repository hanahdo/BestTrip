package sg.vinova.besttrip.library

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import sg.vinova.besttrip.model.autocomplete.BaseAutoComplete
import sg.vinova.besttrip.model.places.BaseObjectResponse

/**
 * Created by Hanah on 11/27/2017.
 */
interface BApi {
    companion object {
        const val place = "place/"
        const val autocomplete = "autocomplete/"
        const val geocode = "geocode/"
        const val directions = "directions/"
        const val nearby = "nearbysearch/"
        var ApiKey = "AIzaSyBvv2dY3Eiq_eBGxLdO6OLvFQnR1yYYG6E"
    }

    @GET(place + autocomplete + "json")
    fun getListLocation(@Query("input") input: String, @Query("key") key: String = ApiKey): Flowable<BaseAutoComplete>

    @GET(geocode + "json")
    fun getAddressByLocation(@Query("latlng") latlng: String, @Query("key") key: String = ApiKey): Flowable<BaseObjectResponse>

    @GET(place + nearby + "json")
    fun getNearbyList(@Query("key") key: String = ApiKey, @Query("radius") radius: Int = 1000, @Query("location") location: String): Flowable<BaseObjectResponse>
}
