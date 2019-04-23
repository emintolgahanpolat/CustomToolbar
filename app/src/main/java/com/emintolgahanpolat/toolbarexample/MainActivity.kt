package com.emintolgahanpolat.toolbarexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        abContainer.createRightButton(R.drawable.ic_android_black_24dp).setOnClickListener{
            Toast.makeText(this,"csacas",Toast.LENGTH_SHORT).show()
        }






        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvButton.text = "Progress : $progress"

                abContainer.toolbarSearchViewHeight(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }




}
