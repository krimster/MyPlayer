package com.kotlin.erickrim.kotlinplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity(), Logger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast("Hello")
        toast("Hello", Toast.LENGTH_LONG)
        applicationContext.toast("hello again")

        d("Hello")
    }

//    private fun toast(message : String) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//    }
}

interface Logger {

    val tag: String
        get() = javaClass.simpleName

    fun d(message: String) = Log.d(tag, message)
}
