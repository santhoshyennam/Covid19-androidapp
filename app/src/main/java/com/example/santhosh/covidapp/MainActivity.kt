package com.example.santhosh.covidapp

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import android.graphics.Color.parseColor
import com.android.volley.toolbox.Volley
import com.example.santhosh.covidapp.R.id.tvCases
import com.leo.simplearcloader.ArcConfiguration
import com.leo.simplearcloader.SimpleArcDialog
import org.eazegraph.lib.models.PieModel
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        con.setOnClickListener {
            startActivity(Intent(this@MainActivity,CountriesActivity::class.java))
        }
        sta.setOnClickListener {
            startActivity(Intent(this@MainActivity,NewsActivity::class.java))

        }
        states.setOnClickListener{
            startActivity(Intent(this@MainActivity,StatesActivity::class.java))

        }
        ts.setOnClickListener {
            startActivity(Intent(this@MainActivity,TelanganaAct::class.java))

        }
        fetchData();
    }

    fun fetchData()
    {
        val mDialog = SimpleArcDialog(this@MainActivity)
        mDialog.setConfiguration(ArcConfiguration(this@MainActivity))
        mDialog.setTitle("Loading..")
        mDialog.show()

        var url="https://corona.lmao.ninja/v2/all/"
        var res = StringRequest(Request.Method.GET,url,
                object: Response.Listener<String>{
                    override fun onResponse(response: String?) {
                        var jsonObject = JSONObject(response)
                        tvCases.setText(jsonObject.getString("cases"))
                        tvRecovered.setText(jsonObject.getString("recovered"))
                        tvCritical.setText(jsonObject.getString("critical"))
                        tvActive.setText(jsonObject.getString("active"))
                        tvTodayCases.setText(jsonObject.getString("todayCases"))
                        tvTotalDeaths.setText(jsonObject.getString("deaths"))
                        tvTodayDeaths.setText(jsonObject.getString("todayDeaths"))
                        tvAffectedCountries.setText(jsonObject.getString("affectedCountries"))
                        pieChart.addPieSlice(PieModel("Cases", Integer.parseInt(tvCases.text.toString()).toFloat(), Color.parseColor("#FFA726")))
                        pieChart.addPieSlice(PieModel("Recoverd", Integer.parseInt(tvRecovered.text.toString()).toFloat(), Color.parseColor("#66BB6A")))
                        pieChart.addPieSlice(PieModel("Deaths", Integer.parseInt(tvTotalDeaths.text.toString()).toFloat(), Color.parseColor("#EF5350")))
                        pieChart.addPieSlice(PieModel("Active", Integer.parseInt(tvActive.text.toString()).toFloat(), Color.parseColor("#29B6F6")))
                        pieChart.startAnimation()
                        mDialog.dismiss()
                    }

                },
                object: Response.ErrorListener{
                    override fun onErrorResponse(error: VolleyError?) {
                        mDialog.dismiss()
                        Toast.makeText(this@MainActivity,"error occured due to internet connection",Toast.LENGTH_LONG).show()
                    }


                })
        var rv = Volley.newRequestQueue(this)
        rv.add(res)

    }
}
