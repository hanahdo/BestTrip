package sg.vinova.besttrip.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by Hanah on 11/29/2017.
 */

object KeyboardUtils {
    /**
     * Auto hide keyboard when touch outside input fields
     *
     * @param activity activity context
     * @param view     root view to receive touch listener
     */
    fun setUpHideSoftKeyboard(activity: Activity, view: View) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { v, event ->
                hideSoftKeyboard(activity)
                false
            }
        }

        if (view is ViewGroup) { //If a layout container, iterate over children and seed recursion.
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setUpHideSoftKeyboard(activity, innerView)
            }
        }
    }

    fun hideSoftKeyboard(activity: Activity?) {
        if (activity == null) {
            return
        }
        val im = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
    }

    fun showSoftKeyboard(activity: Activity?) {
        if (activity == null) {
            return
        }
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInputFromWindow(activity.window.decorView.windowToken,
                InputMethodManager.SHOW_FORCED, 0)
    }

    fun setError(editText: EditText?, error: String) {
        if (editText != null) {
            editText.requestFocus()
            editText.error = error
        }
    }
}