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
class BErrorDialog(context: Context?) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        cancel()
    }

    fun setMessage(mes: String?): BErrorDialog? {
        if (mes == null || TextUtils.isEmpty(mes)) return null
        tvMessage.text = mes
        return this
    }

    init {
        setContentView(R.layout.view_dialog_error)
        tvTitle.text = context!!.resources.getString(R.string.error)
        btnOk.setOnClickListener(this)
    }

}
