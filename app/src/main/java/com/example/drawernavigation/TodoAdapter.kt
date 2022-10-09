package com.example.drawernavigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

//why are we creating the file?-->we want our own custom Adapter
//Why are we creating custom adapter?
class TodoAdapter(var ctx : Context , var customxml : Int , var todoDetails : ArrayList<TodoModel>) : ArrayAdapter<TodoModel>(ctx,customxml,todoDetails) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(ctx)//from here we get to know about xml file
        val view : View = layoutInflater.inflate(customxml,null)
        val txtTitle : TextView = view.findViewById(R.id.txtTitle)
        val txtTask : TextView = view.findViewById(R.id.txtTask)
        txtTitle.text = todoDetails.get(position).title
        txtTask.text = todoDetails.get(position).task
        return view
    }
}