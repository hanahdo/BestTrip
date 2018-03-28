package sg.vinova.besttrip.exts

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import sg.vinova.besttrip.base.BaseBActivity

fun Activity.setUpHideSoftKeyboard(view: View) {
    if (view !is EditText) {
        view.setOnTouchListener { _, _ ->
            hideSoftKeyboard()
            false
        }
    }

    if (view is ViewGroup) {
        for (i in 0 until view.childCount) {
            val innerView = view.getChildAt(i)
            setUpHideSoftKeyboard(innerView)
        }
    }
}

fun Activity.hideSoftKeyboard() {
    val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    im.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}

fun Activity.showSoftKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInputFromWindow(window.decorView.windowToken,
            InputMethodManager.SHOW_FORCED, 0)
}