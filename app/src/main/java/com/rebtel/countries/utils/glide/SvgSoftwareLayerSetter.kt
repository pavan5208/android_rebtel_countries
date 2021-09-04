package com.rebtel.countries.utils.glide

import android.graphics.drawable.PictureDrawable
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.target.Target
import timber.log.Timber

class SvgSoftwareLayerSetter : RequestListener<PictureDrawable> {
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<PictureDrawable>?,
        isFirstResource: Boolean
    ): Boolean {
        Timber.e(e,"Error loading svg image ")
        val view = (target as ImageViewTarget<*>).view
        (view as? ImageView)?.let {
            it.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }
        view.setLayerType(ImageView.LAYER_TYPE_NONE, null)
        return false
    }

    override fun onResourceReady(
        resource: PictureDrawable?,
        model: Any?,
        target: Target<PictureDrawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        val view = (target as ImageViewTarget<*>).view
        (view as? ImageView)?.let {
            it.scaleType = ImageView.ScaleType.FIT_CENTER
        }
        view.setLayerType(ImageView.LAYER_TYPE_SOFTWARE, null)
        return false
    }
}