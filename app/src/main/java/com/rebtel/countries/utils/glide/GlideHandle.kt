package com.rebtel.countries.utils.glide

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.load.engine.DiskCacheStrategy

object GlideHandle {

    fun loadSVG(imageView: ImageView, url: String, @DrawableRes errorImage: Int) {
        val requestBuilder = GlideApp.with(imageView.context)
            .`as`(PictureDrawable::class.java)
            .placeholder(errorImage)
            .error(errorImage)
            .listener(com.rebtel.countries.utils.SvgSoftwareLayerSetter())

        val uri = Uri.parse(url)
        requestBuilder.diskCacheStrategy(DiskCacheStrategy.DATA)
            .load(uri)
            .into(imageView)
    }
}