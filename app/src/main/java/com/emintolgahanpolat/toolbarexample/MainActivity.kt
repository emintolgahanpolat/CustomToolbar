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
        abContainer.createRightButton(R.drawable.ic_android_black_24dp)
        abContainer.createRightButton(R.drawable.ic_android_black_24dp)
        abContainer.createRightButton(R.drawable.ic_android_black_24dp)
    }




}
