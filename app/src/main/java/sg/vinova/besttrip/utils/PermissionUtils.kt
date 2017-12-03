package sg.vinova.besttrip.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import sg.vinova.besttrip.services.BaseCallback


/**
 * Created by Hanah on 12/3/2017.
 */
class PermissionUtils(internal var context: Context) {
    private var currentActivity: Activity

    private var permissionResultCallback: BaseCallback.PermissionResultCallback


    private var permissionList: ArrayList<String> = ArrayList()
    private var listPermissionsNeeded: ArrayList<String> = ArrayList()
    private var dialogContent = ""
    private var reqCode: Int = 0

    init {
        this.context = context
        this.currentActivity = context as Activity

        permissionResultCallback = context as BaseCallback.PermissionResultCallback
    }


    /**
     * Check the API Level & Permission
     *
     * @param permissions
     * @param dialog_content
     * @param requestCode
     */

    fun checkPermission(permissions: ArrayList<String>, dialog_content: String, requestCode: Int) {
        this.permissionList = permissions
        this.dialogContent = dialog_content
        this.reqCode = requestCode

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkAndRequestPermissions(permissions, requestCode)) {
                permissionResultCallback.permissionGranted(requestCode)
                LogUtils.bInfo("all permissions were granted")
                LogUtils.bInfo("proceed to callback")
            }
        } else {
            permissionResultCallback.permissionGranted(requestCode)

            LogUtils.bInfo("all permissions were granted")
            LogUtils.bInfo("proceed to callback")
        }

    }


    /**
     * Check and request the Permissions
     *
     * @param permissions
     * @param requestCode
     * @return
     */

    fun checkAndRequestPermissions(permissions: ArrayList<String>, requestCode: Int): Boolean {

        if (permissions.size > 0) {
            listPermissionsNeeded = ArrayList()

            for (i in 0 until permissions.size) {
                val hasPermission = ContextCompat.checkSelfPermission(currentActivity, permissions[i])

                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(permissions[i])
                }

            }

            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(currentActivity, listPermissionsNeeded.toArray(arrayOfNulls(listPermissionsNeeded.size)), requestCode)
                return false
            }
        }

        return true
    }

    /**
     *
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> if (grantResults.isNotEmpty()) {
                val perms = HashMap<String, Int>()

                for (i in permissions.indices) {
                    perms.put(permissions[i], grantResults[i])
                }

                val pendingPermissions = ArrayList<String>()

                (0 until listPermissionsNeeded.size)
                        .asSequence()
                        .filter { perms[listPermissionsNeeded[it]] != PackageManager.PERMISSION_GRANTED }
                        .forEach {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, listPermissionsNeeded[it]))
                                pendingPermissions.add(listPermissionsNeeded[it])
                            else {
                                LogUtils.bInfo("Go to settings and enable permissions")
                                permissionResultCallback.neverAskAgain(reqCode)
                                Toast.makeText(currentActivity, "Go to settings and enable permissions", Toast.LENGTH_LONG).show()
                                return
                            }
                        }

                if (pendingPermissions.size > 0) {
                    showMessageOKCancel(dialogContent,
                            DialogInterface.OnClickListener { _, which ->
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> checkPermission(permissionList, dialogContent, reqCode)
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        LogUtils.bInfo("permisson not fully given")
                                        if (permissionList.size == pendingPermissions.size)
                                            permissionResultCallback.permissionDenied(reqCode)
                                        else
                                            permissionResultCallback.partialPermissionGranted(reqCode, pendingPermissions)
                                    }
                                }
                            })

                } else {
                    LogUtils.bInfo("all permissions granted")
                    LogUtils.bInfo("proceed to next step")
                    permissionResultCallback.permissionGranted(reqCode)

                }


            }
        }
    }


    /**
     * Explain why the app needs permissions
     *
     * @param message
     * @param okListener
     */
    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(currentActivity)
                .setMessage(message)
                .setPositiveButton("Ok", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show()
    }

}