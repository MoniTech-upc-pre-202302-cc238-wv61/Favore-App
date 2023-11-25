package com.monitech.favore_app.views

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monitech.favore_app.R

class ClientContractDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_contract_details)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }
}