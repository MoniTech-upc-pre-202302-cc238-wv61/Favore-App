package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.models.Post
import com.monitech.favore_app.models.User
import com.monitech.favore_app.services.PostService
import org.w3c.dom.Text

class FreelancerConfigureFavorPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freelancer_configure_favor_post)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        val post_id = intent.getIntExtra("post_id", 0)
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val budgetAmount = intent.getDoubleExtra("budgetAmount", 0.0)

        val postTitle = findViewById<EditText>(R.id.configureFavorTitle)
        val postDescription = findViewById<EditText>(R.id.configureFavorDescription)
        val postBudgetAmount = findViewById<EditText>(R.id.configureFavorBudgetAmount)
        val btnUpdateFavorPost = findViewById<Button>(R.id.btnSaveChangesFavorPost)

        val currentPostId = post_id.toString().toInt()
        postTitle.setText(title)
        postDescription.setText(description)
        postBudgetAmount.setText(budgetAmount.toString())

        val btnBack: ImageButton = findViewById(R.id.btnBack)
        btnBack.setOnClickListener(){
            finish()
        }
        val postService = PostService()

        btnUpdateFavorPost.setOnClickListener {
            val keywords: List<String> = emptyList()
            val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
            val json = sharedPreferences.getString("user", "")
            val user = Gson().fromJson(json, User::class.java)

            val post = Post(
                post_id,
                postTitle.text.toString(),
                postDescription.text.toString(),
                keywords,
                postBudgetAmount.text.toString().toDouble(),
                user
            )
            postService.updatePost(post_id, post) { post ->
                if (post != null) {
                    Toast.makeText(this, "Post updated successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, FreelancerFavorsManagementActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Failed to update post", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}