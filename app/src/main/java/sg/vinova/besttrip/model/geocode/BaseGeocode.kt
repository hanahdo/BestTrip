package sg.vinova.besttrip.model.geocode

import com.google.gson.annotations.SerializedName

/**
 * Created by Hanah on 12/3/2017.
 */
class BaseGeocode {
    @SerializedName("results")
    var results: ArrayList<Result>? = null
    @SerializedName("status")
    var status: String? = null
}