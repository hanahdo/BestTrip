package sg.vinova.besttrip.exts

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadCircleImage(res: Int) {
    Glide.with(context).load(res).apply(RequestOptions().circleCrop().centerCrop()).into(this)
}

fun ImageView.loadCircleImage(res: Uri?) {
    Glide.with(context).load(res).apply(RequestOptions().circleCrop().centerCrop()).into(this)
}

fun ImageView.loadImage(res: Int) {
    Glide.with(context).load(res).into(this)
}