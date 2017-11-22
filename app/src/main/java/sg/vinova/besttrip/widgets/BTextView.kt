package sg.vinova.besttrip.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import sg.vinova.besttrip.R
import java.util.*

/**
 * Created by Hanah on 11/22/2017.
 */
class BTextView : TextView {
    private var fontFamily = "roboto_regular.ttf"
    private var fontSize = 14f

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
            fontFamily = typedArray.getString(R.styleable.BTextView_bFont)
            fontSize = typedArray.getFloat(R.styleable.BTextView_bFontSize, 14f)
        }
        setFont(context, fontFamily, fontSize)
    }

    private fun setFont(context: Context, fontFamily: String?, fontSize: Float) {
        if (fontFamily == null) return
        val tf: Typeface = Typeface.createFromAsset(context.assets, String.format(Locale.US, "fonts/%s", fontFamily))
        typeface = tf
        textSize = fontSize
    }
}