package com.example.santhosh.covidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.indiview1.view.*

class myadap1(var ac:StatesActivity,var ls:MutableList<StateClass>): BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var l = LayoutInflater.from(ac)
        var v = l.inflate(R.layout.indiview1,null)
        v.t1.text=ls.get(p0).state
        v.sc.text=ls.get(p0).statecode
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