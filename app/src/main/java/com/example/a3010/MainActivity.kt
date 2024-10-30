package com.example.a3010

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnExercise1: Button = findViewById(R.id.Ex1)
        val btnExercise2: Button = findViewById(R.id.Ex2)
        val btnExercise3: Button = findViewById(R.id.Ex3)

        btnExercise1.setOnClickListener {
            val intent = Intent(this, Bai1Controller::class.java)
            startActivity(intent)
        }

        btnExercise2.setOnClickListener {
            val intent = Intent(this, Bai2Controller::class.java)
            startActivity(intent)
        }

        btnExercise3.setOnClickListener {
            val intent = Intent(this, Bai3Controller::class.java)
            startActivity(intent)
        }
    }
}

