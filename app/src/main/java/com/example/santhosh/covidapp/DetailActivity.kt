package com.example.santhosh.covidapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var positionCountry = intent.getIntExtra("position",0)
       // Toast.makeText(this@DetailActivity,positionCountry.toString(),Toast.LENGTH_LONG).show()
        getSupportActionBar()!!.setTitle(CountriesActivity.ls.list.get(positionCountry).country);
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);

        tvCases.setText(CountriesActivity.ls.list.get(positionCountry).cases);

        tvRecovered.setText(CountriesActivity.ls.list.get(positionCountry).recovered);

        tvCritical.setText(CountriesActivity.ls.list.get(positionCountry).critical);

        tvActive.setText(CountriesActivity.ls.list.get(positionCountry).active);

        tvTodayCases.setText(CountriesActivity.ls.list.get(positionCountry).todayCases);

        tvTotalDeaths.setText(CountriesActivity.ls.list.get(positionCountry).deaths);

        tvTodayDeaths.setText(CountriesActivity.ls.list.get(positionCountry).todayDeaths);


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item)
    }
}
