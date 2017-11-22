package sg.vinova.besttrip.widgets

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.view_toolbar.view.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.utils.GlideUtils

/**
 * Created by Hanah on 11/22/2017.
 */
class BToolbar : Toolbar {

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) {
        if (context == null) return
        inflate(context, R.layout.view_toolbar, this)
    }

    fun setToolbarTitle(string: String?) {
        if (string == null) return
        tvTitle.text = string
    }

    fun setLeftIcon(drawable: Drawable?) {
        if (drawable == null) return
        ivLeft.visibility = View.VISIBLE
        GlideUtils.loadImage(drawable, context, ivLeft)
    }

    fun setRightIcon(drawable: Drawable?) {
        if (drawable == null) return
        ivRight.visibility = View.VISIBLE
        GlideUtils.loadImage(drawable, context, ivLeft)
    }

    fun onLeftClicked(clickListener: OnClickListener) {

    }

    fun onRightClicked(clickListener: OnClickListener) {

    }

}