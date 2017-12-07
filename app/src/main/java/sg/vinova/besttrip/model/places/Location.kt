package sg.vinova.besttrip.model.places

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Hanah on 12/7/2017.
 */
class Location {
    @SerializedName("lat")
    @Expose
    var lat: Double = 0.0
    @SerializedName("lng")
    @Expose
    var lng: Double = 0.0

    constructor()
    constructor(lat: Double, lng: Double) {
        this.lat = lat
        this.lng = lng
    }
}