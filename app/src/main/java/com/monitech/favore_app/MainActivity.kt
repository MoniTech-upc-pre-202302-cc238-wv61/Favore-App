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

        val btnDemoFreelancerHomepage:Button = findViewById(R.id.btnDemoFreelancerHomepage)
        val btnDemoClientHomepage: Button = findViewById(R.id.btnDemoClientHomepage)
        val btnDemoLogin: Button = findViewById(R.id.btnDemoLogin)

        btnDemoFreelancerHomepage.setOnClickListener(){
            val instance = Intent(this, FreelancerHomeActivity::class.java)
            startActivity(instance)
        }
        btnDemoClientHomepage.setOnClickListener(){
            val instance = Intent(this, ClientHomeActivity::class.java)
            startActivity(instance)
        }

        btnDemoLogin.setOnClickListener(){
            val instance = Intent(this, StartActivity::class.java)
            startActivity(instance)
        }
    }
}