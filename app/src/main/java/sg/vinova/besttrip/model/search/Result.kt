package sg.vinova.besttrip.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Hanah on 11/27/2017.
 */
class Result {
    @SerializedName("formatted_address")
    @Expose
    var formattedAddress: String? = null

    @SerializedName("geometry")
    var geometry: Geometry? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("place_id")
    @Expose
    var placeId: String? = null
}