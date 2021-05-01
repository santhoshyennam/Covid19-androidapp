package com.example.santhosh.covidapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_state_details.*

class StateDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_details)
        var positionCountry = intent.getIntExtra("position1",0)
        // Toast.makeText(this@DetailActivity,positionCountry.toString(),Toast.LENGTH_LONG).show()
        getSupportActionBar()!!.setTitle(StatesActivity.ls.list.get(positionCountry).state);
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);

        tvCases.setText(StatesActivity.ls.list.get(positionCountry).confirmed);

        tvRecovered.setText(StatesActivity.ls.list.get(positionCountry).recovered);

        tvCritical.setText("Dont know");

        tvActive.setText(StatesActivity.ls.list.get(positionCountry).active);

        tvTodayCases.setText(StatesActivity.ls.list.get(positionCountry).deltaconfirmed);

        tvTotalDeaths.setText(StatesActivity.ls.list.get(positionCountry).deaths);

        tvTodayDeaths.setText("Dont know");

    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item)
    }
}
