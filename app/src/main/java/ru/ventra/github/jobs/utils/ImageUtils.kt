package ru.ventra.github.jobs.utils

import android.content.Context
import android.widget.ImageView
import ru.ventra.github.jobs.R
import ru.ventra.github.jobs.ui.glide.GlideApp

object ImageUtils {

    fun centerInsideWithNoPhoto(context: Context, imageView: ImageView, url: String?) {
        GlideApp.with(context)
            .load(url)
            .placeholder(R.drawable.ic_no_photo)
            .centerInside()
            .into(imageView)
    }
}
