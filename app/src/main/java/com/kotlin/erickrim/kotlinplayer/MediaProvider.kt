package com.kotlin.erickrim.kotlinplayer

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

object MediaProvider {

    private val thumbBase = "http://lorempixel.com/400/400/cats/"

    private var data = emptyList<MediaItem>()

    fun dataAsync(callback: (List<MediaItem>) -> Unit ) {
        doAsync {
            if (data.isEmpty()) {
                data = dataSync()
            }
            uiThread {
                callback(data)
            }
        }
    }

    fun dataSync(): List<MediaItem> {
        Thread.sleep(2000)
        return (1..10).map {
            MediaItem(it, "Title $it", "${thumbBase}$it",
                    if (it % 3 == 0) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO)
        }
    }
}


