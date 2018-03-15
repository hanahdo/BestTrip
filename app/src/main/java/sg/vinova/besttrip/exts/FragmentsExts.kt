package sg.vinova.besttrip.exts

import sg.vinova.besttrip.base.BaseBActivity
import sg.vinova.besttrip.base.BaseBFragment

fun BaseBFragment.checkIsAdded() {
    if (!isAdded) return
}

fun BaseBFragment.getBActivity() = activity as BaseBActivity