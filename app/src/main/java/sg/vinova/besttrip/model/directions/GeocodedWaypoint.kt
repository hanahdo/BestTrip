package sg.vinova.besttrip.model.directions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hanah on 10-Dec-17.
 */
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