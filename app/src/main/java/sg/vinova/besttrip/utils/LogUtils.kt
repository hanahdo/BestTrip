package sg.vinova.besttrip.utils

import org.jetbrains.anko.*
import sg.vinova.besttrip.library.Constant

/**
 * Created by Hanah on 12/2/2017.
 */
object LogUtils : AnkoLogger {
    fun bWarn(cls:Class<*>,message: String) {
        val i = "|   ${Constant.appName}: ${cls.simpleName} - $message   |".length
        warn("-".repeat(i))
        warn("|   ${Constant.appName}: ${cls.simpleName} - $message   |")
        warn("-".repeat(i))
    }

    fun bError(cls:Class<*>,message: String) {
        val i = "|   ${Constant.appName}: ${cls.simpleName} - $message   |".length
        error("-".repeat(i))
        error("|   ${Constant.appName}: ${cls.simpleName} - $message   |")
        error("-".repeat(i))
    }

    fun bDebug(cls:Class<*>,message: String) {
        val i = "|   ${Constant.appName}: ${cls.simpleName} - $message   |".length
        debug("-".repeat(i))
        debug("|   ${Constant.appName}: ${cls.simpleName} - $message   |")
        debug("-".repeat(i))
    }

    fun bInfo(cls:Class<*>,message: String) {
        val i = "|   ${Constant.appName}: ${cls.simpleName} - $message   |".length
        info("-".repeat(i))
        info("|   ${Constant.appName}: ${cls.simpleName} - $message   |")
        info("-".repeat(i))
    }

    fun bVerbose(cls:Class<*>,message: String) {
        val i = "|   ${Constant.appName}: ${cls.simpleName} - $message   |".length
        verbose("-".repeat(i))
        verbose("|   ${Constant.appName}: ${cls.simpleName} - $message   |")
        verbose("-".repeat(i))
    }


}
