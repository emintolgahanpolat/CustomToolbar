package com.emintolgahanpolat.toolbarexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        abContainer.createRightButton(R.drawable.ic_android_black_24dp)



        tvButton.setOnClickListener {
            showHide()
        }
    }

    var a=false
    private fun showHide() {
        if (a){
            abContainer.showHideSearchBar(true)
            a=false
        }else{
            abContainer.showHideSearchBar(false)
            a=true
        }

    }


}
