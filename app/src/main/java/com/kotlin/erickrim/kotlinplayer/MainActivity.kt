package com.kotlin.erickrim.kotlinplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    val adapter = MediaAdapter { navigateToDetail(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.adapter = adapter
        loadFilterData(Filter.None)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter: Filter = when (item.itemId) {
            R.id.filter_photos -> Filter.ByType(MediaItem.Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(MediaItem.Type.VIDEO)
            else -> Filter.None
        }
        loadFilterData(filter)
        return true
    }

    private fun loadFilterData(filter: Filter) {
        MediaProvider.dataAsync { media ->
            adapter.items = when (filter) {
                is Filter.None -> media
                is Filter.ByType -> media.filter { it.type == filter.type }
            }
        }

//        async(UI) {
//            val cats = bg { MediaProvider.dataAsync("cats") }
//            val nature = bg { MediaProvider.dataAsync("nature") }
//            adapter.items = cats.await()
//        }

    }

    private fun navigateToDetail(item: MediaItem) {
        startActivity<DetailActivity>(DetailActivity.ID to item.id)
    }

    sealed class Filter {
        object None : Filter()
        class ByType(val type: MediaItem.Type) : Filter()
    }
}
