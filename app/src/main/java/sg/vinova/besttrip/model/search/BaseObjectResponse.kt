package sg.vinova.besttrip.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Hanah on 11/27/2017.
 */
class BaseObjectResponse {
    @SerializedName("results")
    var results: List<Result>? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
}