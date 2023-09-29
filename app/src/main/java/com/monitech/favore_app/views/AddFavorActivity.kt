package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.monitech.favore_app.R
import com.monitech.favore_app.models.Post
import com.monitech.favore_app.services.PostService

class AddFavorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_favor)

        // Service
        val postService = PostService()

        // Elements
        val txtTile:EditText = findViewById(R.id.txtNewFavorTitle)
        val txtDescription:EditText = findViewById(R.id.txtNewFavorDescription)
        val txtBudgetAmount:EditText = findViewById(R.id.txtNewBudgetAmount)
        val btnPublish:Button = findViewById(R.id.btnPublishNewFavor)

        btnPublish.setOnClickListener(){
            // Demo post
            val keywords: List<String> = emptyList()

            val post = Post(
                null,
                txtTile.text.toString(),
                txtDescription.text.toString(),
                keywords,
                txtBudgetAmount.text.toString().toDouble()
            )

            postService.createPost(post){
                if (it?.post_id != null) {
                    Toast.makeText(
                        this,
                        "Post successfully created",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this,
                        "Error creating post",
                        Toast.LENGTH_SHORT).show()
                }
            }

            txtTile.text.clear()
            txtDescription.text.clear()
            txtBudgetAmount.text.clear()
        }
    }
}