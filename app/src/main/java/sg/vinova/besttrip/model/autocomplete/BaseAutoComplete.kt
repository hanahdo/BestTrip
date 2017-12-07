package sg.vinova.besttrip.model.autocomplete

import com.google.gson.annotations.SerializedName

/**
 * Created by Hanah on 12/3/2017.
 */
class BaseAutoComplete {
    @SerializedName("predictions")
    var prediction: ArrayList<Prediction>? = null
    @SerializedName("status")
    var status: String = ""
}
