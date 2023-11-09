package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.models.Post
import com.monitech.favore_app.models.User
import com.monitech.favore_app.services.PostService
import com.squareup.picasso.Picasso

class AddFavorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_favor)

        // Service
        val postService = PostService()

        // Elements
        val txtTile: TextInputLayout = findViewById(R.id.txtNewFavorTitle)
        val txtDescription:TextInputLayout = findViewById(R.id.txtNewFavorDescription)
        val txtBudgetAmount:TextInputLayout = findViewById(R.id.txtNewBudgetAmount)
        val btnPublish:Button = findViewById(R.id.btnPublishNewFavor)
        val photoFavor:ImageView = findViewById(R.id.imgNewFavor)
        Picasso.get()
            .load("https://static.wixstatic.com/media/76751ad539344a41a9950d2ee585e350.jpg/v1/fill/w_560,h_372,al_c,q_80,usm_0.66_1.00_0.01,enc_auto/Contractor.jpg")
            .into(photoFavor)

        btnPublish.setOnClickListener(){
            // Demo post
            val keywords: List<String> = emptyList()

            val sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE)
            val json = sharedPreferences.getString("user", "")
            val user = Gson().fromJson(json, User::class.java)

            val post = Post(
                null,
                txtTile.editText?.text.toString(),
                txtDescription.editText?.text.toString(),
                keywords,
                txtBudgetAmount.editText?.text.toString().toDouble(),
                user,
            )

            postService.createPost(post){
                if (it?.post_id != null) {
                    Toast.makeText(
                        this,
                        "Post successfully created",
                        Toast.LENGTH_SHORT).show()
                    val instance = Intent(this, FreelancerFavorsManagementActivity::class.java)
                    startActivity(instance)
                } else {
                    Toast.makeText(
                        this,
                        "Error creating post",
                        Toast.LENGTH_SHORT).show()
                }
            }

            txtTile.clearAnimation()
            txtDescription.clearAnimation()
            txtBudgetAmount.clearAnimation()
        }
    }
}