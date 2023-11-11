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
import com.monitech.favore_app.services.PostService

class ServiceDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)

        val backButton = findViewById<ImageButton>(R.id.backButton)

        val contractService = ContractService()
        val postService = PostService()


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

        btnAddRequest.setOnClickListener() {
            val requestDescription = requestDescription.text.toString()
            val requestAmount = requestAmount.text.toString().toDouble()

            val sharedPreferences = getSharedPreferences("favore", MODE_PRIVATE)
            val json = sharedPreferences.getString("user", "")
            var client: User

            Log.d("JsonData", "Json data: $json")

            if (json?.isNotEmpty() == true) {
                client = Gson().fromJson(json, User::class.java)
            } else {
                client = User(
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
            }

            Log.d("ClientData", "Client data: $client")


            if (!requestDescription.isNullOrBlank() && !requestAmount.isNaN()) {
                postService.getPostById(postId) { obtainedPost ->
                    if (obtainedPost != null) {
                        Log.d("GetPostById", "Post obtenido: $obtainedPost")

                        var contract: Contract? = null

                        val freelancerRequest = obtainedPost.user
                        val requestCategory = Category(
                            1,
                            "string",
                            "string"
                        )

                        try {

                            contract = Contract(
                                1,
                                requestDescription,
                                "pending",
                                "string",
                                requestAmount,
                                requestCategory,
                                freelancerRequest,
                                client,
                                obtainedPost
                            )

                            Log.d("ContractCreation", "Contract to create: $contract")

                            contractService.createContract(contract) { createdContract ->
                                try {
                                    if (createdContract?.contract_id != null) {
                                        Toast.makeText(
                                            this,
                                            "Contract successfully created",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Error creating contract",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } catch (f: Exception) {
                                    Log.e(
                                        "ContractCreationError",
                                        "Error handling contract response: ${f.message}",
                                    )

                                    Toast.makeText(
                                        this,
                                        "Error handling contract response: ${f.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        } catch (e: Exception) {
                            Log.e("ContractCreationError", "Error creating contract: ${e.message}")
                        }
                    } else {
                        Log.e("PostError", "Couldn't get post: $postId")
                    }
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