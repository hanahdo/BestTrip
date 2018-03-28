package sg.vinova.besttrip.model.autocomplete

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Prediction {
    @SerializedName("description")
    @Expose
    var description: String = ""
    @SerializedName("place_id")
    @Expose
    var placeId: String = ""
}
