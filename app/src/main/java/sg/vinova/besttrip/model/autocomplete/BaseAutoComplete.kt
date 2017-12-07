package sg.vinova.besttrip.model.autocomplete

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Hanah on 12/3/2017.
 */
class BaseAutoComplete {
    @SerializedName("predictions")
    @Expose
    var predictions: ArrayList<Prediction>? = null
    @SerializedName("status")
    @Expose
    var status: String = ""
}
