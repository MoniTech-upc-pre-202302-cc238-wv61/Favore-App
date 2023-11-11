package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
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
        val txtKeywords:TextInputLayout = findViewById(R.id.txtNewKeywords)
        val chipGroup:ChipGroup = findViewById(R.id.keywordsChipGroup)

        val btnBack: ImageButton = findViewById(R.id.btnBack)
        btnBack.setOnClickListener(){
            finish()
        }

        // when user writes space in txtkeywrods add it into the chip group
        txtKeywords.editText?.setOnKeyListener { _, _, _ ->
            if (txtKeywords.editText?.text.toString().contains(" ")) {
                val chip = Chip(this)
                chip.text = txtKeywords.editText?.text.toString().replace(" ", "")
                chip.isCloseIconVisible = true
                chip.setOnCloseIconClickListener {
                    chipGroup.removeView(chip)
                }
                chipGroup.addView(chip)
                txtKeywords.editText?.text?.clear()
            }
            false
        }


        Picasso.get()
            .load("https://static.wixstatic.com/media/76751ad539344a41a9950d2ee585e350.jpg/v1/fill/w_560,h_372,al_c,q_80,usm_0.66_1.00_0.01,enc_auto/Contractor.jpg")
            .into(photoFavor)

        btnPublish.setOnClickListener(){
            // Insert chips into keywords
            val keywords: List<String> = chipGroup.children.map { chip ->
                (chip as Chip).text.toString()
            }.toList()

            val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
            val json = sharedPreferences.getString("user", "")
            val user = Gson().fromJson(json, User::class.java)

            val post = Post(
                null,
                txtTile.editText?.text.toString(),
                txtDescription.editText?.text.toString(),
                keywords,
                txtBudgetAmount.editText?.text.toString().toDoubleOrNull() ?: 0.0,
                user,
            )

            if (post.user.type == "CLIENT") {
                Toast.makeText(
                    this,
                    "Error creating favor",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                postService.createPost(post){
                    if (it?.post_id != null) {
                        Toast.makeText(
                            this,
                            "Post successfully created",
                            Toast.LENGTH_SHORT).show()
                        txtTile.editText?.text?.clear()
                        txtDescription.editText?.text?.clear()
                        txtBudgetAmount.editText?.text?.clear()
                        txtKeywords.editText?.text?.clear()
                        chipGroup.removeAllViews()

                        val instance = Intent(this, FreelancerFavorsManagementActivity::class.java)
                        startActivity(instance)
                    } else {
                        Toast.makeText(
                            this,
                            "Error creating post",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}