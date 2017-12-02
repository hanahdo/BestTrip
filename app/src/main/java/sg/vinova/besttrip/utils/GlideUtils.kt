package sg.vinova.besttrip.utils

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Hanah on 11/22/2017.
 */
object GlideUtils {
    fun loadImage(resource: Int, activity: Activity, imageView: ImageView) {
        Glide.with(activity).load(resource).into(imageView)
    }

    fun loadImage(resource: Int, fragment: Fragment, imageView: ImageView) {
        Glide.with(fragment).load(resource).into(imageView)
    }

    fun loadImage(resource: Int, context: Context, imageView: ImageView) {
        Glide.with(context).load(resource).into(imageView)
    }

    fun loadImageFromURL(resource: Uri?, activity: Activity, imageView: ImageView, placeHolder: Int = 0) {
        Glide.with(activity).load(resource).apply(RequestOptions().fitCenter().placeholder(placeHolder)).into(imageView)
    }
}