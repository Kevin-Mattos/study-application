package com.example.studyapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coreui.SampleFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.container, SampleFragment()).commit()
    }
}
