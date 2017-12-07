package sg.vinova.besttrip.model.places

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Hanah on 12/7/2017.
 */

class Geometry {
    @SerializedName("location")
    @Expose
    var location: Location? = null
}