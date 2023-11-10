package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.models.Category
import com.monitech.favore_app.models.Contract
import com.monitech.favore_app.models.Post
import com.monitech.favore_app.models.User
import com.monitech.favore_app.services.ContractService
import java.text.SimpleDateFormat
import java.util.Date

class ServiceDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)

        val backButton = findViewById<ImageButton>(R.id.backButton)

        val contractService = ContractService()

        backButton.setOnClickListener {
            finish()
        }

        val postId = intent.getIntExtra("post_id", -1)
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val budgetAmount = intent.getDoubleExtra("budgetAmount", 0.0)

        val requestAmount = findViewById<EditText>(R.id.bidEditText)
        val requestDescription = findViewById<EditText>(R.id.descriptionEditText)

        val titleTextView: TextView = findViewById(R.id.service_name)
        titleTextView.text = title

        val descriptionTextView: TextView = findViewById(R.id.service_description)
        descriptionTextView.text = description

        val btnAddRequest: Button = findViewById(R.id.btnAddRequest)

        btnAddRequest.setOnClickListener(){

            val requestDescription = requestDescription.text.toString()
            val requestAmount = requestAmount.text.toString().toDouble()

            if (!requestDescription.isNullOrBlank() && !requestAmount.isNaN()) {

                try {

                    //testing data
                    val client = User(
                        12,
                        "ramiro",
                        "ramiro",
                        "ramiro",
                        "ramiro",
                        "2023-10-28T04:35:04.575663",
                        "2023-10-28T04:35:04.575668",
                        "",
                        true,
                        "CLIENT"
                    )

                    val freelancer = User(
                        1,
                        "user1",
                        "string",
                        "string",
                        "string",
                        "2023-09-16T03:25:47.616263",
                        "2023-09-16T03:25:47.61628",
                        "string",
                        true,
                        "FREELANCER"
                    )

                    val requestCategory = Category(
                        1,
                        "string",
                        "string"
                    )

                    val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
                    val json = sharedPreferences.getString("user", "")
                    val user = Gson().fromJson(json, User::class.java)
                    val requestPost = Post(
                        1,
                        "House Cleaning",
                        "I will clean your house",
                        listOf("house", "cleaning"),
                        100.0,
                        user
                    )

                    val contract = Contract(
                        2,
                        requestDescription,
                        "pending",
                        "string",
                        requestAmount,
                        requestCategory,
                        freelancer,
                        client,
                        requestPost
                    )

                    contractService.createContract(contract)
                    {
                        if (it?.contract_id != null) {
                            Toast.makeText(
                                this,
                                "Contract successfully created",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                "Error creating contract and $postId",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    Log.e("ContractCreationError", "Error creating contract: ${e.message}")
                }


            } else {
                Toast.makeText(
                    this,
                    "Invalid description or amount. Please fill out all fields correctly.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.selectedItemId = R.id.navigation_home
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, ClientHomeActivity::class.java))
                    finish()
                }
                R.id.navigation_orders -> {
                    startActivity(Intent(this, FreelancerFavorsManagementActivity::class.java))
                    finish()
                }
                R.id.navigation_search -> {
                    startActivity(Intent(this, SearchServiceActivity::class.java))
                    finish()
                }
                R.id.navigation_inbox -> {
                    startActivity(Intent(this, AddFavorActivity::class.java))
                    finish()
                }
                R.id.navigation_user -> {
                    startActivity(Intent(this, SignInClientActivity::class.java))
                    finish()
                }
            }
            true
        }
    }
}