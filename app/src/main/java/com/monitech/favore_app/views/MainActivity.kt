package com.monitech.favore_app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monitech.favore_app.R
import android.widget.Button
import com.monitech.favore_app.views.AddFavorActivity
import com.monitech.favore_app.views.ClientHomeActivity
import com.monitech.favore_app.views.FreelancerHomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDemoFreelancerHomepage:Button = findViewById(R.id.btnDemoFreelancerHomepage)
        val btnDemoClientHomepage: Button = findViewById(R.id.btnDemoClientHomepage)

        btnDemoFreelancerHomepage.setOnClickListener(){
            val instance = Intent(this, FreelancerHomeActivity::class.java)
            startActivity(instance)
        }
        btnDemoClientHomepage.setOnClickListener(){
            val instance = Intent(this, ClientHomeActivity::class.java)
            startActivity(instance)
        }
    }
}