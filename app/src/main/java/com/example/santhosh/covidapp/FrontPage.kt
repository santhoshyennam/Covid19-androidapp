package com.example.santhosh.covidapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class FrontPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front_page)
        supportActionBar!!.hide()
        var h = Handler()

        h.postDelayed(
                object : Runnable{
                    override fun run() {
                        startActivity(Intent(this@FrontPage,MainActivity::class.java))
                        finish()
                    }

                },3000
        )

    }
}