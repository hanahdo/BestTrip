@file:Suppress("unused")

package sg.vinova.besttrip.exts

import android.content.Context
import android.util.Log
import sg.vinova.besttrip.library.Constant
import sg.vinova.besttrip.widgets.dialogs.BErrorDialog

inline fun <reified T> T.warning(message: String) {
    val i = "|   ${T::class.java.simpleName} - $message   |"
    Log.w(Constant.appName, "-".repeat(i.length))
    Log.w(Constant.appName, i)
    Log.w(Constant.appName, "-".repeat(i.length))
}

inline fun <reified T> T.error(message: String) {
    val i = "|   ${T::class.java.simpleName} - $message   |"
    Log.e(Constant.appName, "-".repeat(i.length))
    Log.e(Constant.appName, i)
    Log.e(Constant.appName, "-".repeat(i.length))
}

inline fun <reified T> T.errorWithDialog(context: Context, message: String) {
    val i = "|   ${T::class.java.simpleName} - $message   |"
    Log.e(Constant.appName, "-".repeat(i.length))
    Log.e(Constant.appName, i)
    Log.e(Constant.appName, "-".repeat(i.length))

    BErrorDialog(context).apply {
        setMessage(message)
        show()
    }
}

inline fun <reified T> T.info(message: String) {
    val i = "|   ${T::class.java.simpleName} - $message   |"
    Log.i(Constant.appName, "-".repeat(i.length))
    Log.i(Constant.appName, i)
    Log.i(Constant.appName, "-".repeat(i.length))
}

inline fun <reified T> T.debug(message: String) {
    val i = "|   ${T::class.java.simpleName} - $message   |"
    Log.d(Constant.appName, "-".repeat(i.length))
    Log.d(Constant.appName, i)
    Log.d(Constant.appName, "-".repeat(i.length))
}

inline fun <reified T> T.verbose(message: String) {
    val i = "|   ${T::class.java.simpleName} - $message   |"
    Log.v(Constant.appName, "-".repeat(i.length))
    Log.v(Constant.appName, i)
    Log.v(Constant.appName, "-".repeat(i.length))
}