package com.monitech.favore_app.views

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        val post_id = intent.getIntExtra("post_id", 0)
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val budgetAmount = intent.getDoubleExtra("budgetAmount", 0.0)

        val postTitle = findViewById<EditText>(R.id.configureFavorTitle)
        val postDescription = findViewById<EditText>(R.id.configureFavorDescription)
        val postBudgetAmount = findViewById<EditText>(R.id.configureFavorBudgetAmount)
        val postId = findViewById<TextView>(R.id.favorPostId)
        val btnUpdateFavorPost = findViewById<Button>(R.id.btnSaveChangesFavorPost)

        val currentPostId = post_id.toString().toInt()
        postId.setText("Post id: ${post_id.toString()}")
        postTitle.setText(title)
        postDescription.setText(description)
        postBudgetAmount.setText(budgetAmount.toString())

        val postService = PostService()


        btnUpdateFavorPost.setOnClickListener {
            // Keywords are not implemented yet

            val keywords: List<String> = emptyList()
            val sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE)
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
                    finish()
                } else {
                    Toast.makeText(this, "Failed to update post", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}