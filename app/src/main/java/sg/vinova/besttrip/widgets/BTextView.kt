package sg.vinova.besttrip.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import sg.vinova.besttrip.R
import java.util.*

/**
 * Created by Hanah on 11/22/2017.
 */
class BTextView : TextView {
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
        val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BTextView, defStyleAttr, defStyleRes)

        /**
         * Font family
         * Default is Roboto Regular
         */
        when (typedArray.getInt(R.styleable.BTextView_bFont, 0)) {
            0 -> fontFamily = resources.getString(R.string.roboto_regular)
            1 -> fontFamily = resources.getString(R.string.roboto_light)
            2 -> fontFamily = resources.getString(R.string.roboto_bold)
            3 -> fontFamily = resources.getString(R.string.roboto_medium)
        }
        setFont(context, fontFamily)

        /**
         * Set text alignment
         * Default alignment is Center
         */
        textAlignment = when (typedArray.getInt(R.styleable.BTextView_bTextAlign, 0)) {
            0 -> View.TEXT_ALIGNMENT_CENTER
            1 -> View.TEXT_ALIGNMENT_TEXT_START
            2 -> View.TEXT_ALIGNMENT_TEXT_END
            else -> View.TEXT_ALIGNMENT_CENTER
        }

        /**
         * Set text color
         * Default is #FFFFFF (white)
         */
        setTextColor(typedArray.getColor(R.styleable.BTextView_bFontColor, fontColor))

        /**
         * Set background color
         * Default is 0
         */
        setBackgroundColor(typedArray.getColor(R.styleable.BTextView_bBackgroundColor, 0))

        /**
         * Set background
         * Default is null
         */
        background =
                if (typedArray.getDrawable(R.styleable.BTextView_bBackground) == null) null
                else typedArray.getDrawable(R.styleable.BTextView_bBackground)
    }

    private fun setFont(context: Context, fontFamily: String?) {
        if (fontFamily == null) return
        val tf: Typeface = Typeface.createFromAsset(context.assets, String.format(Locale.US, "fonts/%s", fontFamily))
        typeface = tf
    }
}