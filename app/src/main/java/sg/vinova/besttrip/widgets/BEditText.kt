package sg.vinova.besttrip.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import sg.vinova.besttrip.R
import java.util.*

/**
 * Created by Hanah on 11/23/2017.
 */
class BEditText : EditText {
    private var fontFamily = ""
    private var fontColor = ContextCompat.getColor(context, R.color.background)

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
        val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BEditText, defStyleAttr, defStyleRes)

        /**Font family
         * Default is Roboto Regular
         */
        fontFamily = when (typedArray.getInt(R.styleable.BEditText_bFont, 0)) {
            0 -> resources.getString(R.string.roboto_regular)
            1 -> resources.getString(R.string.roboto_light)
            2 -> resources.getString(R.string.roboto_bold)
            3 -> resources.getString(R.string.roboto_medium)
            else -> resources.getString(R.string.roboto_regular)
        }
        setFont(context, fontFamily)

        /**Set text color
         * Default is #271D5D (background)
         */
        setTextColor(typedArray.getColor(R.styleable.BEditText_bFontColor, fontColor))

        /**Set background color
         * Default is 0
         */
        setBackgroundColor(typedArray.getColor(R.styleable.BEditText_bBackgroundColor, 0))

        /** Set background
         *  Default is bg_b_edit_text.xml
         */
        background =
                if (typedArray.getDrawable(R.styleable.BEditText_bBackground) == null)
                    resources.getDrawable(R.drawable.bg_b_edit_text, context.theme)
                else typedArray.getDrawable(R.styleable.BEditText_bBackground)

        /** Set text alignment
         *  Default alignment is Left
         */
        textAlignment = when (typedArray.getInt(R.styleable.BEditText_bTextAlign, 1)) {
            0 -> View.TEXT_ALIGNMENT_CENTER
            1 -> View.TEXT_ALIGNMENT_TEXT_START
            2 -> View.TEXT_ALIGNMENT_TEXT_END
            else -> View.TEXT_ALIGNMENT_CENTER
        }

        /** Set hint color and hint text
         *  Default hint color is #271D5D (background)
         *  Default hint text is ''
         */
        hint =
                if (TextUtils.isEmpty(typedArray.getString(R.styleable.BEditText_bHint))) ""
                else typedArray.getString(R.styleable.BEditText_bHint)
        setHintTextColor(typedArray.getColor(R.styleable.BEditText_bFontColor, fontColor))


    }

    private fun setFont(context: Context, fontFamily: String?) {
        if (fontFamily == null) return
        val tf: Typeface = Typeface.createFromAsset(context.assets, String.format(Locale.US, "fonts/%s", fontFamily))
        typeface = tf
    }
}