package sg.vinova.besttrip.model.directions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hanah on 10-Dec-17.
 */
class Route {
    @SerializedName("bounds")
    @Expose
    var bounds: Bounds? = null
    @SerializedName("copyrights")
    @Expose
    var copyrights: String? = null
    @SerializedName("legs")
    @Expose
    var legs: List<Leg>? = null
    @SerializedName("overview_polyline")
    @Expose
    var overviewPolyline: OverviewPolyline? = null
    @SerializedName("summary")
    @Expose
    var summary: String? = null
    @SerializedName("warnings")
    @Expose
    var warnings: List<Any>? = null
    @SerializedName("waypoint_order")
    @Expose
    var waypointOrder: List<Any>? = null
}