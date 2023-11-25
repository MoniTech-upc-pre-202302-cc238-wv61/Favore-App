package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.monitech.favore_app.R
import com.monitech.favore_app.models.Contract
import com.monitech.favore_app.models.User
import com.monitech.favore_app.services.ContractService
import java.text.SimpleDateFormat
import java.util.Date

class NewRequestActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_request)
        loadUserAndConfigureNavBar()

        val contractService = ContractService()


        val btnReturn: ImageButton = findViewById(R.id.backButton)
        btnReturn.setOnClickListener(){
            finish()
        }

    }
}