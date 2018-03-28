package sg.vinova.besttrip.exts

import android.support.v4.app.Fragment

inline fun <reified T : Fragment> T.checkIsAdded() {
    if (!isAdded) return
}