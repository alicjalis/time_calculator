package com.example.kalkulatorczasu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dates = findViewById<Button>(R.id.dates)
        val time = findViewById<Button>(R.id.time)
        dates.setOnClickListener {
            val intent = Intent(this, DatesActivity::class.java)
            startActivity(intent)
        }

        time.setOnClickListener {
            val intent = Intent(this, TimeActivity::class.java)
            startActivity(intent)
        }


    }
}