package com.kotlin.erickrim.kotlinplayer

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun RecyclerView.ViewHolder.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    itemView.context.toast(message, length)
}

fun ViewGroup.inflate(@LayoutRes resId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resId, this, attachToRoot)
}

fun ImageView.loadUrl(imageUrl: String) {
    Picasso.get().load(imageUrl).into(this)
}

inline fun <reified T: View> View.find(resId: Int): T {
    return findViewById(resId) as T
}

inline fun <reified T: View> RecyclerView.ViewHolder.find(resId: Int): T {
    return itemView.find(resId) as T
}