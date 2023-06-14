package com.ubaya.adv160420067week4.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.adv160420067week4.R


fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url).resize(400, 400).centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
            }
        })

}
fun createNotificationChannel(context: Context, importance: Int, showBadge:Boolean, name: String, description: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        channel.setShowBadge(showBadge)

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)

    }
}

@BindingAdapter("android:imgUrl", "android:progressBar")
fun loadPhotoURL(view: ImageView, url: String?, pb:ProgressBar)
{
    view.loadImage(url,pb)
}
//@BindingAdapter("android:paddingLeft")
//fun setPaddingLeft(view: View, padding: Int) {
//    view.setPadding(padding,
//        view.getPaddingTop(),
//        view.getPaddingRight(),
//        view.getPaddingBottom())
//}
//@BindingAdapter("android:paddingLeft")
//fun setPaddingLeft(view: View, padding: Int) {
//    view.setPadding(padding,
//        view.getPaddingTop(),
//        view.getPaddingRight(),
//        view.getPaddingBottom())
//}


