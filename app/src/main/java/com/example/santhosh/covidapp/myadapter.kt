package com.example.santhosh.covidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.indiview.view.*

class myadapter(var ac:CountriesActivity,var ls:MutableList<CountriesClass>): BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var l = LayoutInflater.from(ac)
        var v = l.inflate(R.layout.indiview,null)
        v.t1.text=ls.get(p0).country
        Glide.with(ac).load(ls.get(p0).flag).into(v.iview)
        return v
    }

    override fun getItem(p0: Int): Any {
            return 0
    }

    override fun getItemId(p0: Int): Long {
            return 0
    }

    override fun getCount(): Int {
        return ls.size
    }
}