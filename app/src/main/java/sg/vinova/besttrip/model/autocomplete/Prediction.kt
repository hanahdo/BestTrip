package sg.vinova.besttrip.model.autocomplete

import com.google.gson.annotations.SerializedName

/**
 * Created by Hanah on 12/3/2017.
 */
class Prediction {
    @SerializedName("description")
    var description: String? = null
    @SerializedName("place_id")
    var placeId: String? = null
}