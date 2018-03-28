package sg.vinova.besttrip.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Toolbar
import kotlinx.android.synthetic.main.view_toolbar.view.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.exts.loadImage
import sg.vinova.besttrip.services.BaseListener

class BToolbar : Toolbar {

    //    private var fontFamily = ""
    var listener: BaseListener.OnToolbarClickListener? = null

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
        /**
         * Bind layout
         */
        inflate(context, R.layout.view_toolbar, this)

        /**
         * Get attribute set
         */
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BToolbar, defStyleAttr, 0)

        /**
         * Set title
         */
        tvTitle.text = typedArray.getString(R.styleable.BToolbar_bTitle)

        /**
         * Font family
         * Default is Roboto Regular
         */
//        fontFamily = when (typedArray.getInt(R.styleable.BButton_bFont, 0)) {
//            0 -> resources.getString(R.string.roboto_regular)
//            1 -> resources.getString(R.string.roboto_light)
//            2 -> resources.getString(R.string.roboto_bold)
//            3 -> resources.getString(R.string.roboto_medium)
//            else -> resources.getString(R.string.roboto_regular)
//        }
//        setFont(context, fontFamily)

        typedArray.recycle()
    }

//    private fun setFont(context: Context, fontFamily: String?) {
//        if (fontFamily == null) return
//        val tf: Typeface = Typeface.createFromAsset(context.assets, String.format(Locale.US, "fonts/%s", fontFamily))
//        tvTitle.typeface = tf
//    }

    fun setToolbarTitle(string: String?): BToolbar? {
        if (string == null) return null
        tvTitle.text = string
        return this
    }

    fun hideLeftIcon() {
        ivLeft.visibility = View.INVISIBLE
    }

    fun hideRightIcon() {
        ivRight.visibility = View.INVISIBLE
    }

    fun setLeftIcon(drawable: Int): BToolbar? {
        if (drawable == 0) return null
        ivLeft.apply {
            visibility = View.VISIBLE
            ivLeft.loadImage(drawable)
            if (listener != null) setOnClickListener({ listener!!.onLeftClick() })
        }
        return this
    }

    fun setRightIcon(drawable: Int): BToolbar? {
        if (drawable == 0) return null
        ivRight.apply {
            visibility = View.VISIBLE
            loadImage(drawable)
            if (listener != null) setOnClickListener({ listener?.onRightClick() })
        }
        return this
    }
}
