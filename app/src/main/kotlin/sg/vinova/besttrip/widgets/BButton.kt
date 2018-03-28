package sg.vinova.besttrip.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import sg.vinova.besttrip.R
import java.util.*

class BButton : Button {
    private var fontFamily = ""
    private var fontColor = ContextCompat.getColor(context, R.color.white)

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) {
        if (context == null) return
        val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BButton, defStyleAttr, defStyleRes)

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

        /**
         * Set text color
         * Default is #FFFFFF (white)
         */
        setTextColor(typedArray.getColor(R.styleable.BButton_bFontColor, fontColor))

        /**
         * Set background color
         * Default is 0
         */
        setBackgroundColor(typedArray.getColor(R.styleable.BButton_bBackgroundColor, 0))

        /**
         * Set background
         * Default is null
         */
        background =
                if (typedArray.getDrawable(R.styleable.BButton_bBackground) == null) null
                else typedArray.getDrawable(R.styleable.BButton_bBackground)

        /**
         * Set text alignment
         * Default alignment is Center
         */
        textAlignment = when (typedArray.getInt(R.styleable.BButton_bTextAlign, 0)) {
            0 -> View.TEXT_ALIGNMENT_CENTER
            1 -> View.TEXT_ALIGNMENT_TEXT_START
            2 -> View.TEXT_ALIGNMENT_TEXT_END
            else -> View.TEXT_ALIGNMENT_CENTER
        }

        /**
         * Disable all caps text in button
         */
        setAllCaps(false)

        typedArray.recycle()
    }

    private fun setFont(context: Context, fontFamily: String?) {
        if (fontFamily == null) return
        val tf: Typeface = Typeface.createFromAsset(context.assets, "fonts/$fontFamily")
        typeface = tf
    }
}
