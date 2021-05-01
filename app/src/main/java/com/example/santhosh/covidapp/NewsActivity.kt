package com.example.santhosh.covidapp

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_news.*
import android.widget.FrameLayout
import android.webkit.WebChromeClient
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.MenuItem


class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        getSupportActionBar()!!.setTitle("News")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);



        var listener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var i = Intent(Intent.ACTION_VIEW)
                var x = ""
                when (p0!!.id) {
                    R.id.tv5 -> x="https://www.youtube.com/watch?v=NezGUhHsl_8"
                    R.id.tv9 ->x="https://www.youtube.com/watch?v=Q6QR4979KIQ"
                    R.id.tv10 -> x="https://www.youtube.com/watch?v=yA1P_udg-uU"
                    R.id.etv -> x="https://www.youtube.com/watch?v=HUiHKse-YgM"
                    R.id.abn -> x="https://www.youtube.com/watch?v=-6KFZdO8_pM"
                    R.id.v6 -> x="https://www.youtube.com/watch?v=bl9VaUaI0r0"
                    R.id.sakshi -> x="https://www.youtube.com/watch?v=8McTsOqeueE"
                    R.id.vn -> x = "https://www.youtube.com/watch?v=NNYTYjFwGIQ"
                    R.id.hm -> x = "https://www.youtube.com/watch?v=naAzroMRrJ8"
                    R.id.tn -> x="https://www.youtube.com/watch?v=8XvCJSijPrw"
                }
                i.setData(Uri.parse(x))
                startActivity(i)
            }
        }
        tv5.setOnClickListener(listener)
        tv9.setOnClickListener(listener)
        tv10.setOnClickListener(listener)
        etv.setOnClickListener(listener)
        abn.setOnClickListener(listener)
        v6.setOnClickListener(listener)
        sakshi.setOnClickListener(listener)
        vn.setOnClickListener(listener)
        hm.setOnClickListener(listener)
        tn.setOnClickListener(listener)





    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item)
    }

}

