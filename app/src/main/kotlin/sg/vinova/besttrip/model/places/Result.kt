package sg.vinova.besttrip.model.places

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("geometry")
    @Expose
    var geometry: Geometry? = null
    @SerializedName("formatted_address")
    @Expose
    var address1: String = ""
    @SerializedName("name")
    @Expose
    var name: String = ""
    @SerializedName("place_id")
    @Expose
    var placeId: String = ""
    @SerializedName("vicinity")
    @Expose
    var address2: String = ""
}
