package sg.vinova.besttrip.exts

import android.util.Log
import sg.vinova.besttrip.library.Constant

fun Class<*>.warning(message: String = "") {
    val i = "|   $simpleName - $message   |".length
    Log.w(Constant.appName, "-".repeat(i))
    Log.w(Constant.appName, "|   $simpleName - $message   |")
    Log.w(Constant.appName, "-".repeat(i))
}

fun Class<*>.error(message: String = "") {
    val i = "|   $simpleName - $message   |".length
    Log.e(Constant.appName, "-".repeat(i))
    Log.e(Constant.appName, "|   $simpleName - $message   |")
    Log.e(Constant.appName, "-".repeat(i))
}

fun Class<*>.info(message: String = "") {
    val i = "|   $simpleName - $message   |".length
    Log.i(Constant.appName, "-".repeat(i))
    Log.i(Constant.appName, "|   $simpleName - $message   |")
    Log.i(Constant.appName, "-".repeat(i))
}

fun Class<*>.debug(message: String = "") {
    val i = "|   $simpleName - $message   |".length
    Log.d(Constant.appName, "-".repeat(i))
    Log.d(Constant.appName, "|   $simpleName - $message   |")
    Log.d(Constant.appName, "-".repeat(i))
}

fun Class<*>.verbose(message: String = "") {
    val i = "|   $simpleName - $message   |".length
    Log.v(Constant.appName, "-".repeat(i))
    Log.v(Constant.appName, "|   $simpleName - $message   |")
    Log.v(Constant.appName, "-".repeat(i))
}