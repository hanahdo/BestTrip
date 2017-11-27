package sg.vinova.besttrip.widgets.dialogs

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.view_dialog_error.*
import sg.vinova.besttrip.R

/**
 * Created by hanah on 11/27/17.
 */
class BDialog(context: Context?) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        dismiss()
    }

    private fun init(context: Context?) {
        if (context == null) return
        setContentView(R.layout.view_dialog_error)
        tvTitle.text = context.resources.getString(R.string.error)
        btnOk.setOnClickListener(this)
    }

    fun setTitle(title: String?) {
        if (title == null || TextUtils.isEmpty(title)) return
        tvTitle.text = title
    }

    fun setMessage(mes: String?): BDialog? {
        if (mes == null || TextUtils.isEmpty(mes)) return null
        tvMessage.text = mes
        return this
    }

    init {
        init(context)
    }


}
