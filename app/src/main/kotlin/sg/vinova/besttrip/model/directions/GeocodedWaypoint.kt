package sg.vinova.besttrip.model.directions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GeocodedWaypoint {
    @SerializedName("geocoder_status")
    @Expose
    var geocoderStatus: String = ""
    @SerializedName("place_id")
    @Expose
    var placeId: String = ""
    @SerializedName("types")
    @Expose
    var types: List<String>? = null
}