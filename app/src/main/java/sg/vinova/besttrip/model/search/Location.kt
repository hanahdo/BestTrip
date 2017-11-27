package sg.vinova.besttrip.model.search

import com.google.gson.annotations.SerializedName


/**
 * Created by Hanah on 11/27/2017.
 */
class Location {
    @SerializedName("lat")
    var lat: Double? = null
    @SerializedName("lng")
    var lng: Double? = null
}