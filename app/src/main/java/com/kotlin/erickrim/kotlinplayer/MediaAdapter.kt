package com.kotlin.erickrim.kotlinplayer

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_media_item.view.*
import kotlin.properties.Delegates

typealias Listener = (MediaItem) -> Unit

class MediaAdapter(items: List<MediaItem> = emptyList(), val listener: Listener)
    : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var items: List<MediaItem> by Delegates.observable(items) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = parent.inflate(R.layout.view_media_item)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: MediaItem) = with(itemView) {
            media_title.text = item.title
            media_image.loadUrl(item.thumbUrl)
            media_video_indicator.visibility = when (item.type) {
                MediaItem.Type.PHOTO -> View.GONE
                MediaItem.Type.VIDEO -> View.VISIBLE
            }
        }
    }
}