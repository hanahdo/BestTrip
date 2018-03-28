package sg.vinova.besttrip.exts

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

inline fun <reified T : ImageView> T.loadCircleImage(res: Int) {
    Glide.with(context).load(res).apply(RequestOptions().circleCrop().centerCrop()).into(this)
}

inline fun <reified T : ImageView> T.loadCircleImage(res: Uri?) {
    Glide.with(context).load(res).apply(RequestOptions().circleCrop().centerCrop()).into(this)
}

inline fun <reified T : ImageView> T.loadImage(res: Int) {
    Glide.with(context).load(res).into(this)
}