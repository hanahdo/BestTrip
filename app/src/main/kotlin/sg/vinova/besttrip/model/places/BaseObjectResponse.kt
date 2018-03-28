package sg.vinova.besttrip.model.places

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseObjectResponse {
    @SerializedName("html_attributions")
    @Expose
    var htmlAttributions: ArrayList<String>? = null
    @SerializedName("next_page_token")
    @Expose
    var nextPageToken: String = ""
    @SerializedName("results")
    @Expose
    var results: ArrayList<Result>? = null
    @SerializedName("status")
    @Expose
    var status: String = ""
}