package com.monitech.favore_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.monitech.favore_app.views.ClientHomeActivity
import com.monitech.favore_app.views.FreelancerHomeActivity
import com.monitech.favore_app.views.StartActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        finish()
        val instance = Intent(this, StartActivity::class.java)
        startActivity(instance)
    }
}