package com.monitech.favore_app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.android.material.bottomnavigation.BottomNavigationView
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