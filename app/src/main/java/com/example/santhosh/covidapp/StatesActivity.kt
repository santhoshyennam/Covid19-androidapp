package com.example.santhosh.covidapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_states.*
import org.json.JSONArray
import org.json.JSONObject

class StatesActivity : AppCompatActivity() {
    object ls {
        var list = mutableListOf<StateClass>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_states)
        getSupportActionBar()!!.setTitle("Statewise in India")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);

        fetchdata()
        lsview.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var i = Intent(this@StatesActivity,StateDetails::class.java)
                i.putExtra("position1",p2)
                startActivity(i)
            }

        })
    }

    private fun fetchdata() {
        val mDialog = SimpleArcDialog(this@StatesActivity)
        mDialog.setConfiguration(ArcConfiguration(this@StatesActivity))
        mDialog.setTitle("Loading..")
        mDialog.show()

        var url = "https://api.covid19india.org/data.json"

        var res= StringRequest(Request.Method.GET,url,
                object: Response.Listener<String>{
                    override fun onResponse(response: String?) {
                        var jsonObj = JSONObject(response)
                        var jsonarray = jsonObj.getJSONArray("statewise")
                        var i=0
                        while (i<jsonarray.length())
                        {
                           var jsonobj = jsonarray.getJSONObject(i)
                            var active = jsonobj.getString("active")
                            var confirmed =jsonobj.getString("confirmed")
                            var deaths = jsonobj.getString("deaths")
                            var deltaconfirmed =jsonobj.getString("deltaconfirmed")
                           var recovered =jsonobj.getString("recovered")
                            /*var lastupdatedtime=jsonObj.getString("lastupdatedtime")
                            var deltadeaths =jsonobj.getString("deltadeaths")
                            var deltarecovered = jsonObj.getString("deltarecovered")*/
                            var state =jsonobj.getString("state")
                            var statecode =jsonobj.getString("statecode")
                            var s=StateClass(active,confirmed,deaths,deltaconfirmed,"s","s","s",recovered,state,statecode)
                            ls.list.add(s)
                            i++
                        }
                        //Toast.makeText(this@StatesActivity,"e"+i.toString(), Toast.LENGTH_LONG).show()

                        lsview.adapter=myadap1(this@StatesActivity, ls.list)
                        mDialog.dismiss()
                    }

                }
                ,
                object: Response.ErrorListener{
                    override fun onErrorResponse(error: VolleyError?) {
                        mDialog.dismiss()
                        Toast.makeText(this@StatesActivity,"error occured due to internet connection", Toast.LENGTH_LONG).show()

                    }

                })
        var requestQueue = Volley.newRequestQueue(this@StatesActivity);

        requestQueue.add(res);
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item)
    }
}
