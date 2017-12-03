package sg.vinova.besttrip.services

/**
 * Created by Hanah on 12/3/2017.
 */
interface BaseCallback {
    interface PermissionResultCallback {
        fun permissionGranted(requestCode: Int)
        fun partialPermissionGranted(requestCode: Int, grantedPermissions: ArrayList<String>)
        fun permissionDenied(requestCode: Int)
        fun neverAskAgain(requestCode: Int)
    }
}