package sg.vinova.besttrip.services


interface BaseListener {
    interface OnToolbarClickListener {
        fun onLeftClick()
        fun onRightClick()
    }

    interface OnItemClickListener<T> {
        fun onItemClick(t: T)
    }
}