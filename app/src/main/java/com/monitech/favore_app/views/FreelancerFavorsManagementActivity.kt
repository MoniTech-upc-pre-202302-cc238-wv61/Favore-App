package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.adapter.FavorPostAdapter
import com.monitech.favore_app.models.Post
import com.monitech.favore_app.models.User
import com.monitech.favore_app.services.PostService

class FreelancerFavorsManagementActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freelancer_favors_management)
        loadUserAndConfigureNavBar()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        // Service
        val postService = PostService()

        val txtNoFavorsToShow:TextView = findViewById(R.id.txtFreelancerNoFavors)

        val favorPostRecycler:RecyclerView = findViewById(R.id.recyclerFreelancerFavorPost)
        val posts: List<Post>

        val btnBack: ImageButton = findViewById(R.id.btnBack)
        btnBack.setOnClickListener(){
            finish()
        }

        postService.getAllPosts { posts ->
            val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
            val json = sharedPreferences.getString("user", "")
            val user = Gson().fromJson(json, User::class.java)

            val filteredPosts = posts.filter { post -> post.user.id == user.id }.reversed()


            if (filteredPosts.isNotEmpty()) {
                favorPostRecycler.layoutManager = LinearLayoutManager(applicationContext)
                favorPostRecycler.adapter = FavorPostAdapter(filteredPosts)

                favorPostRecycler.adapter = FavorPostAdapter(filteredPosts).apply {
                    setOnItemClickListener { post ->
                        val intent = Intent(this@FreelancerFavorsManagementActivity, FreelancerConfigureFavorPost::class.java)
                        intent.putExtra("post_id", post.post_id)
                        intent.putExtra("title", post.title)
                        intent.putExtra("description", post.description)
                        intent.putExtra("budgetAmount", post.budgetAmount)
                        startActivity(intent)
                    }
                }
            } else {
                txtNoFavorsToShow.text="No favors to show. Try adding a new favor!"
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
                    startActivity(Intent(this, UserProfileActivity::class.java))
                    finish()
                }
            }
            true
        }

    }
}