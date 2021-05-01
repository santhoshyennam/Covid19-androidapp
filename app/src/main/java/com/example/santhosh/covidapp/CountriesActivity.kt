package com.example.santhosh.covidapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_countries.*
import org.json.JSONArray
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.example.santhosh.covidapp.CountriesActivity.ls.list
import com.leo.simplearcloader.ArcConfiguration
import com.leo.simplearcloader.SimpleArcDialog


class CountriesActivity : AppCompatActivity() {

     object ls {
         var list = mutableListOf<CountriesClass>()
     }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        getSupportActionBar()!!.setTitle("Countrywise")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);



        fetchdata()

        lview.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var i =Intent(this@CountriesActivity,DetailActivity::class.java)
                i.putExtra("position",p2)
                startActivity(i)
            }

        })
    }

    private fun fetchdata() {
        val mDialog = SimpleArcDialog(this@CountriesActivity)
        mDialog.setConfiguration(ArcConfiguration(this@CountriesActivity))
        mDialog.setTitle("Loading..")
        mDialog.show()

        var url="https://corona.lmao.ninja/v2/countries/"
        var res = StringRequest(Request.Method.GET,url,
                object : Response.Listener<String>{
                    override fun onResponse(response: String?) {
                        var jsonarray = JSONArray(response)
                        var i=0
                        while(i<jsonarray.length())
                        {
                            var jsonobj = jsonarray.getJSONObject(i)
                            var country = jsonobj.getString("country")
                            var cases =jsonobj.getString("cases")
                            var todayCases =jsonobj.getString("todayCases")
                            var deaths = jsonobj.getString("deaths")
                            var todayDeaths =jsonobj.getString("todayDeaths")
                            var active =jsonobj.getString("active")
                            var recovered =jsonobj.getString("recovered")
                            var critical =jsonobj.getString("critical")
                            var flagobj = jsonobj.getJSONObject("countryInfo")
                            var flag = flagobj.getString("flag")
                            var c = CountriesClass(flag,country,cases,todayCases,deaths,todayDeaths,recovered,active,critical)
                            list.add(c)
                            i++
                        }
                        lview.adapter=myadapter(this@CountriesActivity, list)
                        mDialog.dismiss()


                        //Toast.makeText(this@CountriesActivity,"yes", Toast.LENGTH_LONG).show()

                    }

                },
                object: Response.ErrorListener{
                    override fun onErrorResponse(error: VolleyError?) {
                        mDialog.dismiss()
                        Toast.makeText(this@CountriesActivity,"error occured due to internet connection", Toast.LENGTH_LONG).show()

                    }

                })

        var requestQueue = Volley.newRequestQueue(this@CountriesActivity);

        requestQueue.add(res);
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item)
    }

}
