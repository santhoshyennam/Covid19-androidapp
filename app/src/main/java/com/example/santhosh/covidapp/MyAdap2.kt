package com.example.santhosh.covidapp

import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.indiview1.view.*


class MyAdap2(var activity: TelanganaAct, var lis:MutableList<StateClass>): RecyclerView.Adapter<MyAdap2.Myholder>() {
    override fun onBindViewHolder(p0: Myholder, p1: Int) {

        p0.state!!.text = lis[p1].state.toUpperCase()
        p0.statecode!!.text = lis[p1].statecode


    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyAdap2.Myholder {
        var inf = LayoutInflater.from(activity)
        var v = inf.inflate(R.layout.indiview1, p0, false)
        var myholder = MyAdap2.Myholder(v)
        v.setOnClickListener {
            var i = Intent(activity,DistrDet::class.java)
            var p1 =myholder.adapterPosition
            var a = arrayOf(lis[p1].confirmed,lis[p1].recovered,lis[p1].deaths,lis[p1].deltaconfirmed,
                    lis[p1].deltadeaths,lis[p1].deltarecovered,lis[p1].state,lis[p1].active)
            i.putExtra("array",a)
            activity.startActivity(i)
            //Toast.makeText(activity,lis[myholder.adapterPosition].active,Toast.LENGTH_LONG).show()

        }
        return myholder
    }

    override fun getItemCount(): Int {
        return lis.size
    }

    class Myholder(v: View) : RecyclerView.ViewHolder(v) {

        var state: TextView? = null
        var statecode: TextView? = null
        init {
            state = v.t1
            statecode = v.sc

        }

    }
}
