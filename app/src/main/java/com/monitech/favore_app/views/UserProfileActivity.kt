package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.models.User
import com.squareup.picasso.Picasso

class UserProfileActivity : BaseActivity() {

    private fun showSignOutDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Do you want to remove your session?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                // Delete the user session from SharedPreferences
                val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
                sharedPreferences.edit().remove("user").apply()

                // Redirect to SignInClientActivity
                val intent = Intent(this, SignInClientActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Sign Out")
        alert.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        loadUserAndConfigureNavBar()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val profilePicture: ImageView = findViewById(R.id.profilePicture)
        val profileImg: ImageView = findViewById(R.id.imgProfile)

        val sharedPreferences = getSharedPreferences("favore", MODE_PRIVATE)
        val json = sharedPreferences.getString("user", "")
        val user = Gson().fromJson(json, User::class.java)

        if (user.imageUrl != null && user.imageUrl != ""){
            Picasso.get()
                .load(user.imageUrl)
                .into(profilePicture);
        }
        else {
            Picasso.get()
                .load("https://cdn-icons-png.flaticon.com/512/10015/10015419.png")
                .into(profilePicture);
        }

        if (user.imageUrl != null && user.imageUrl != ""){
            Picasso.get()
                .load(user.imageUrl)
                .into(profileImg);
        }
        else {
            Picasso.get()
                .load("https://cdn-icons-png.flaticon.com/512/10015/10015419.png")
                .into(profileImg);
        }

        val btnGoPreferences: TextView = findViewById(R.id.txtPreferences)
        btnGoPreferences.setOnClickListener {
            val instance = Intent(this, PreferencesActivity::class.java)
            startActivity(instance)
        }

        val btnGoTermsAndConditions: TextView = findViewById(R.id.txtTermsAndConditions)
        btnGoTermsAndConditions.setOnClickListener {
            val instance = Intent(this, TermsAndConditionsActivity::class.java)
            startActivity(instance)
        }

        val txtSignOut: TextView = findViewById(R.id.txtSignOut)
        txtSignOut.setOnClickListener {
            showSignOutDialog()
        }
    }
}