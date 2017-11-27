package sg.vinova.besttrip.widgets

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.Toolbar
import kotlinx.android.synthetic.main.view_toolbar.view.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.services.BaseListener
import sg.vinova.besttrip.utils.GlideUtils
import java.util.*

/**
 * Created by Hanah on 11/22/2017.
 */
class BToolbar : Toolbar {

    private var fontFamily = ""
    lateinit var listener: BaseListener.OnToolbarClickListener

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
        //Bind layout
        inflate(context, R.layout.view_toolbar, this)

        //Get attribute set
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BToolbar, defStyleAttr, 0)

        //Set title
        tvTitle.text = typedArray.getString(R.styleable.BToolbar_bTitle)

        //Font family, default is Roboto Regular
        fontFamily = when (typedArray.getInt(R.styleable.BButton_bFont, 0)) {
            0 -> resources.getString(R.string.roboto_regular)
            1 -> resources.getString(R.string.roboto_light)
            2 -> resources.getString(R.string.roboto_bold)
            3 -> resources.getString(R.string.roboto_medium)
            else -> resources.getString(R.string.roboto_regular)
        }
        setFont(context, fontFamily)
    }

    private fun setFont(context: Context, fontFamily: String?) {
        if (fontFamily == null) return
        val tf: Typeface = Typeface.createFromAsset(context.assets, String.format(Locale.US, "fonts/%s", fontFamily))
        tvTitle.typeface = tf
    }

    fun setToolbarTitle(string: String?) {
        if (string == null) return
        tvTitle.text = string
    }

    fun hideLeftIcon() {
        ivLeft.visibility = View.INVISIBLE
    }

    fun hideRightIcon() {
        ivRight.visibility = View.INVISIBLE
    }

    fun setLeftIcon(drawable: Int) {
        GlideUtils.loadImage(drawable, context, ivLeft)
        ivLeft.setOnClickListener({ listener.onLeftClick() })
    }

    fun setRightIcon(drawable: Int) {
        GlideUtils.loadImage(drawable, context, ivRight)
        ivRight.setOnClickListener({ listener.onRightClick() })
    }
}
