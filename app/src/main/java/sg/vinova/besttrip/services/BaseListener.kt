package sg.vinova.besttrip.services

import android.view.View

/**
 * Created by Hanah on 11/22/2017.
 */
interface BaseListener {
    interface OnToolbarClickListener {
        fun onLeftClick(clickListener: View.OnClickListener)
        fun onRightClick(clickListener: View.OnClickListener)
    }
}