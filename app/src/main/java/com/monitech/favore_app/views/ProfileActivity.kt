package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monitech.favore_app.R

class ProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        loadUserAndConfigureNavBar()
    }
}