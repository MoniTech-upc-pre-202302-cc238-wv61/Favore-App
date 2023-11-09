package com.monitech.favore_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.gson.Gson
import com.monitech.favore_app.models.User
import com.monitech.favore_app.views.ClientHomeActivity
import com.monitech.favore_app.views.FreelancerHomeActivity
import com.monitech.favore_app.views.StartActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        finish()

        val sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE)

        // Check if theres a user logged in
        if (sharedPreferences.contains("user")) {
            val json = sharedPreferences.getString("user", "")
            val user = Gson().fromJson(json, User::class.java)

            if (user.type == "CLIENT") {
                val instance = Intent(this, ClientHomeActivity::class.java)
                startActivity(instance)
            } else if (user.type  == "FREELANCER") {
                val instance = Intent(this, FreelancerHomeActivity::class.java)
                startActivity(instance)
            }
        } else {
            val instance = Intent(this, StartActivity::class.java)
            startActivity(instance)
        }
    }
}