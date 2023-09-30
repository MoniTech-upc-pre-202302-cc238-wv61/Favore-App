package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.monitech.favore_app.R
import com.monitech.favore_app.adapter.FavorPostAdapter
import com.monitech.favore_app.models.Post
import com.monitech.favore_app.services.PostService

class FreelancerFavorsManagementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freelancer_favors_management)

        // Service
        val postService = PostService()

        val txtNoFavorsToShow:TextView = findViewById(R.id.txtFreelancerNoFavors)

        postService.getAllPosts { posts ->
            if (posts != null) {
                val favorPostRecycler:RecyclerView = findViewById(R.id.recyclerFreelancerFavorPost)
                favorPostRecycler.layoutManager = LinearLayoutManager(applicationContext)
                favorPostRecycler.adapter = FavorPostAdapter(posts)
            } else {
                txtNoFavorsToShow.text="No favors to show"
            }
        }
    }
}