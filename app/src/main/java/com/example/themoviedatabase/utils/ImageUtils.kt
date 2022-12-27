package com.example.themoviedatabase.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.themoviedatabase.R
import java.io.File
import kotlin.math.roundToInt

fun Context.loadImageCircle(imageView: ImageView, url: Any?, placeholder: Int) {
    Glide.with(this)
        .load(url ?: placeholder)
        .circleCrop()
        .placeholder(placeholder)
        .error(placeholder)
        .into(imageView)
}

fun Context.loadImageRoundCorner(url: Any?, @DimenRes roundingRadiusId: Int, placeholder: Int): RequestBuilder<Drawable> {
    val roundingRadius = resources.getDimension(roundingRadiusId).roundToInt()
    return Glide.with(this)
        .load(url ?: placeholder)
        .transform(CenterCrop(), RoundedCorners(roundingRadius))
        .placeholder(placeholder)
}

fun Fragment.loadImageRoundCorner(url: Any?, @DimenRes roundingRadiusId: Int, placeholder: Int): RequestBuilder<Drawable> {
    return requireContext().loadImageRoundCorner(url, roundingRadiusId, placeholder)
}

fun Context.loadImageRoundCorner(imageView: ImageView, url: Any?, @DimenRes roundingRadiusId: Int, placeholder: Int) {
    loadImageRoundCorner(url, roundingRadiusId, placeholder)
        .into(imageView)
}

fun Fragment.loadImageRoundCorner(imageView: ImageView, url: Any?, @DimenRes roundingRadiusId: Int, placeholder: Int) {
    requireContext().loadImageRoundCorner(imageView, url, roundingRadiusId, placeholder)
}

fun Fragment.loadImageCircle(imageView: ImageView, path: Any?, placeholder: Int) {
    Glide.with(this)
        .load(path ?: placeholder)
        .circleCrop()
        .placeholder(placeholder)
        .error(placeholder)
        .into(imageView)
}

fun Fragment.loadImageCircle(imageView: ImageView, file: File, placeholder: Int) {
    Glide.with(this)
        .load(file)
        .circleCrop()
        .placeholder(placeholder)
        .into(imageView)
}

fun Fragment.loadImage(imageView: ImageView, url: String?) {
    Glide.with(this)
        .load(url)
        .into(imageView)
}

fun Fragment.loadImage(imageView: ImageView, url: String?, placeholder: Int = R.mipmap.ic_launcher) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .into(imageView)
}

fun Context.loadImage(imageView: ImageView, url: Any) {
    Glide.with(this)
        .load(url)
        .into(imageView)
}

fun Context.loadImage(imageView: ImageView, imgResource: Int, placeholder: Int = R.mipmap.ic_launcher) {
    Glide.with(this)
        .load(imgResource)
        .placeholder(placeholder)
        .into(imageView)
}

fun Context.downloadImage(url: Any?) {
    Glide.with(this)
        .downloadOnly()
        .load(url)
        .submit()
}

fun ImageView.loadImage(url: String?) {
    if (url == null) return
    Glide.with(this)
        .load(url)
        .into(this)
}