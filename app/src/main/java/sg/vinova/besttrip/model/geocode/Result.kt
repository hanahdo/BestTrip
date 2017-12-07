package sg.vinova.besttrip.model.geocode

import com.google.gson.annotations.SerializedName

/**
 * Created by Hanah on 12/3/2017.
 */
class Result {
    @SerializedName("formatted_address")
    var address: String = ""
    @SerializedName("place_id")
    var placeId: String = ""
}
