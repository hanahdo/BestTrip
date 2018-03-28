package sg.vinova.besttrip.widgets.dialogs

import android.app.Dialog
import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.view_dialog_submit.*
import sg.vinova.besttrip.R

class BSubmitDialog(context: Context) : Dialog(context) {
    var listener: View.OnClickListener? = null

    init {
        setContentView(R.layout.view_dialog_submit)
        tvTitle.text = context.resources.getString(R.string.exit)
        tvMessage.text = context.resources.getString(R.string.exit_message)
        if (listener != null) {
            btnOk.setOnClickListener(listener)
            btnCancel.setOnClickListener(listener)
        }
    }
}