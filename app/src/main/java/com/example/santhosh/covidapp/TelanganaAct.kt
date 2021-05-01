package com.example.santhosh.covidapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.leo.simplearcloader.ArcConfiguration
import com.leo.simplearcloader.SimpleArcDialog
import kotlinx.android.synthetic.main.activity_telangana.*
import org.json.JSONObject

class TelanganaAct : AppCompatActivity() {

    object ls {
        var list = mutableListOf<StateClass>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telangana)
        getSupportActionBar()!!.setTitle("Telangana")
        var lm= LinearLayoutManager(this@TelanganaAct, LinearLayoutManager.VERTICAL,false)
        lview.layoutManager=lm

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);

        fetchdata()

    }

    private fun fetchdata() {
        val mDialog = SimpleArcDialog(this@TelanganaAct)
        mDialog.setConfiguration(ArcConfiguration(this@TelanganaAct))
        mDialog.setTitle("Loading..")
        mDialog.show()

        var url = "https://api.covid19india.org/state_district_wise.json"
        var a = arrayOf("Adilabad","Bhadradri Kothagudem","Hyderabad","Jagtial","Jangaon","Jayashankar Bhupalapally","Jogulamba Gadwal",
                                    "Kamareddy","Karimnagar","Khammam","Komaram Bheem","Mahabubabad","Mahabubnagar","Mancherial","Medak",
                "Medchal Malkajgiri","Mulugu","Nagarkurnool","Nalgonda","Narayanpet","Nirmal","Nizamabad","Peddapalli","Rajanna Sircilla",
                "Ranga Reddy","Sangareddy","Siddipet","Suryapet","Vikarabad","Wanaparthy","Warangal Rural","Warangal Urban","Yadadri Bhuvanagiri","Unknown")
        var res= StringRequest(Request.Method.GET,url,
                object: Response.Listener<String>{
                    override fun onResponse(response: String?) {
                        var jsonObj = JSONObject(response)
                        var jsonarray = jsonObj.getJSONObject("Telangana").getJSONObject("districtData")
                        var i=0
                        while (i<a.size)
                        {
                            var jsonobj = jsonarray.getJSONObject(a[i])
                            var active = jsonobj.getString("active")
                            var confirmed =jsonobj.getString("confirmed")
                            var deaths = jsonobj.getString("deceased")
                            var delta = jsonobj.getJSONObject("delta")
                            var deltaconfirmed =delta.getString("confirmed")
                            var recovered =jsonobj.getString("recovered")
                            //var lastupdatedtime=jsonObj.getString("lastupdatedtime")
                            var deltadeaths =delta.getString("deceased")
                            var deltarecovered = delta.getString("recovered")
                             var s=StateClass(active,confirmed,deaths,deltaconfirmed,deltadeaths,deltarecovered,"s",recovered,a[i],a[i].substring(0,2))
                            ls.list.add(s)
                            i++
                        }

                        lview.adapter=MyAdap2(this@TelanganaAct,ls.list)
                        mDialog.dismiss()

                    }

                }
                ,
                object: Response.ErrorListener{
                    override fun onErrorResponse(error: VolleyError?) {
                        mDialog.dismiss()
                        Toast.makeText(this@TelanganaAct,"error occured due to internet connection", Toast.LENGTH_LONG).show()

                    }

                })
        var requestQueue = Volley.newRequestQueue(this@TelanganaAct);

        requestQueue.add(res);
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item)
    }


}
