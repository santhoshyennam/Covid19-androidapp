package com.example.santhosh.covidapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_state_details.*

class DistrDet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_details)
        var a = intent.getStringArrayExtra("array")
        // Toast.makeText(this@DetailActivity,positionCountry.toString(),Toast.LENGTH_LONG).show()
        getSupportActionBar()!!.setTitle(a[6]);
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);

        tvCases.setText(a[0]);

        tvRecovered.setText(a[1]);

        tvCritical.setText("Dont know");

        tvActive.setText(a[7]);

        tvTodayCases.setText(a[3]);

        tvTotalDeaths.setText(a[2]);

        tvTodayDeaths.setText(a[4]);

    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item)
    }
}
