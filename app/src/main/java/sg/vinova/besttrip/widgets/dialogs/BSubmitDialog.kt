package sg.vinova.besttrip.widgets.dialogs

import android.app.Dialog
import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.view_dialog_submit.*
import sg.vinova.besttrip.R

/**
 * Created by Hanah on 12/2/2017.
 */
class BSubmitDialog(context: Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnOk -> onBackPressed()
            R.id.btnCancel -> cancel()
        }
    }

    init {
        setContentView(R.layout.view_dialog_submit)
        tvTitle.text = context.resources.getString(R.string.exit)
        tvMessage.text = context.resources.getString(R.string.exit_message)
        btnOk.setOnClickListener(this)
        btnCancel.setOnClickListener(this)
    }
}