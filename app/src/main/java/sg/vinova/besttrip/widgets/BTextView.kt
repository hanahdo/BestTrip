package sg.vinova.besttrip.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.TextView
import sg.vinova.besttrip.R
import java.util.*

/**
 * Created by Hanah on 11/22/2017.
 */
class BTextView : TextView {
    private var fontFamily = ""

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    private fun init(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) {
        if (context == null) return
        val typedArray: TypedArray
        if (attrs != null) {
            typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BTextView, defStyleAttr, defStyleRes)
            when (typedArray.getInt(R.styleable.BTextView_bFont, 0)) {
                0 -> fontFamily = resources.getString(R.string.roboto_regular)
                1 -> fontFamily = resources.getString(R.string.roboto_light)
                2 -> fontFamily = resources.getString(R.string.roboto_bold)
                3 -> fontFamily = resources.getString(R.string.roboto_medium)
            }
        }
        setFont(context, fontFamily)
    }

    private fun setFont(context: Context, fontFamily: String?) {
        if (fontFamily == null) return
        val tf: Typeface = Typeface.createFromAsset(context.assets, String.format(Locale.US, "fonts/%s", fontFamily))
        typeface = tf
        setTextColor(ContextCompat.getColor(context, R.color.white))
    }
}