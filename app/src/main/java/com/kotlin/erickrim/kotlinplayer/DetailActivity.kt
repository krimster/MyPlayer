package com.kotlin.erickrim.kotlinplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        val ID = "DetailActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra(ID, -1)

        MediaProvider.dataAsync { media ->
            media.find { it.id == id }?.let { item ->
                supportActionBar?.title = item.title
                detail_thumb.loadUrl(item.thumbUrl)
                detail_video_indicator.visibility =  when(item.type) {
                    MediaItem.Type.PHOTO -> View.GONE
                    MediaItem.Type.VIDEO -> View.VISIBLE
                }
            }
        }
    }
}
