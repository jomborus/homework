package com.otus.activity1

import android.content.Intent
import android.content.Intent.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        findViewById<Button>(R.id.button_open_a).setOnClickListener {
            val intent = Intent(this, ActivityA::class.java)
            intent.addFlags(FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
        findViewById<Button>(R.id.button_open_d).setOnClickListener {
            val intent = Intent(this, ActivityD::class.java)
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        findViewById<Button>(R.id.button_close_c).setOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.button_close_stack).setOnClickListener {
            val intent = Intent(this, ActivityA::class.java)
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
    }
}
