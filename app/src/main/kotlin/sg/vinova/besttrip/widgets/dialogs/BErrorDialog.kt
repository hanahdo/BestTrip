package sg.vinova.besttrip.widgets.dialogs

import android.app.Dialog
import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.view_dialog_error.*
import sg.vinova.besttrip.R

class BErrorDialog(context: Context?) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        cancel()
    }

    fun setMessage(mes: String?) {
        if (mes.isNullOrEmpty()) return
        tvMessage.text = mes
    }

    init {
        setContentView(R.layout.view_dialog_error)
        tvTitle.text = context!!.resources.getString(R.string.error)
        btnOk.setOnClickListener(this)
    }

}
