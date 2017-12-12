package sg.vinova.besttrip.model.directions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hanah on 10-Dec-17.
 */
class BaseDirections {
    @SerializedName("geocoded_waypoints")
    @Expose
    var geocodedWaypoints: List<GeocodedWaypoint>? = null
    @SerializedName("routes")
    @Expose
    var routes: List<Route>? = null
    @SerializedName("status")
    @Expose
    var status: String = ""
}