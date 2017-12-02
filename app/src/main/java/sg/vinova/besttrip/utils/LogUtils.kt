package sg.vinova.besttrip.utils

import org.jetbrains.anko.*
import sg.vinova.besttrip.library.Constant

/**
 * Created by Hanah on 12/2/2017.
 */
object LogUtils : AnkoLogger {
    fun bWarn(message: String) {
        val i = "|   ${Constant.appName}: $message   |".length
        warn("-".repeat(i))
        warn("|   ${Constant.appName}: $message   |")
        warn("-".repeat(i))
    }

    fun bError(message: String) {
        val i = "|   ${Constant.appName}: $message   |".length
        error("-".repeat(i))
        error("|   ${Constant.appName}: $message   |")
        error("-".repeat(i))
    }

    fun bDebug(message: String) {
        val i = "|   ${Constant.appName}: $message   |".length
        debug("-".repeat(i))
        debug("|   ${Constant.appName}: $message   |")
        debug("-".repeat(i))
    }

    fun bInfo(message: String) {
        val i = "|   ${Constant.appName}: $message   |".length
        info("-".repeat(i))
        info("|   ${Constant.appName}: $message   |")
        info("-".repeat(i))
    }

    fun bVerbose(message: String) {
        val i = "|   ${Constant.appName}: $message   |".length
        verbose("-".repeat(i))
        verbose("|   ${Constant.appName}: $message   |")
        verbose("-".repeat(i))
    }


}