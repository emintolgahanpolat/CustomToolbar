package com.emintolgahanpolat.toolbarexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    var list= mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        abContainer.createRightButton(R.drawable.ic_android_black_24dp).setOnClickListener {
            Toast.makeText(this, "csacas", Toast.LENGTH_SHORT).show()
        }

       createDummy()


        rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvList.adapter = CustomRecyclerViewAdapter(this,list as MutableList<Any>)

        abContainer.setListView(rvList)
    }

    private fun createDummy() {
        for (i in 1..16){
            list.add(Model(R.drawable.person,"$i title","$i  subtitle"))
        }

    }


}
