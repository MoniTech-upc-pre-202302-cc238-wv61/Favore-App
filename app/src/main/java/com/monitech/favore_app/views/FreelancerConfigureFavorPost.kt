package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.monitech.favore_app.R
import com.monitech.favore_app.models.Post
import com.monitech.favore_app.services.PostService

class FreelancerConfigureFavorPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freelancer_configure_favor_post)

        val postTitle = findViewById<EditText>(R.id.configureFavorTitle)
        val postDescription = findViewById<EditText>(R.id.configureFavorDescription)
        val postBudgetAmount = findViewById<EditText>(R.id.configureFavorBudgetAmount)
        val postId = findViewById<EditText>(R.id.favorPostId)
        val btnUpdateFavorPost = findViewById<EditText>(R.id.btnSaveChangesFavorPost)

        val post_id = intent.getIntExtra("post_id", 0)
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val budgetAmount = intent.getDoubleExtra("budgetAmount", 0.0)

        postId.setText(post_id.toString())
        postTitle.setText(title)
        postDescription.setText(description)
        postBudgetAmount.setText(budgetAmount.toString())

        val postService = PostService()


        btnUpdateFavorPost.setOnClickListener {
            // Keywords are not implemented yet

            val keywords: List<String> = emptyList()
            val post = Post(
                post_id,
                postTitle.text.toString(),
                postDescription.text.toString(),
                keywords,
                postBudgetAmount.text.toString().toDouble()
            )
            postService.updatePost(post_id, post) { post ->
                if (post != null) {
                    finish()
                }
            }
        }
    }
}