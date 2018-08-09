package com.nosetrap.once

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nosetrap.oncelib.Once
import com.nosetrap.oncelib.RunOnce
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            Once(this,1).execute(object : RunOnce {
                override fun run() {
                    Log.d("Once","1")
                }
            })
        }

        btn3.setOnClickListener {
            Once(this,3).execute(object : RunOnce{
                override fun run() {
                    Log.d("Once","3")
                }
            })
        }

        btn2.setOnClickListener {
            Once(this,2).execute(object : RunOnce{
                override fun run() {
                    Log.d("Once","2")
                }
            })
        }

        btn4.setOnClickListener {
            Once(this,4).execute(object : RunOnce {
                override fun run() {
                    Log.d("Once","4")
                }
            })
        }
    }
}
