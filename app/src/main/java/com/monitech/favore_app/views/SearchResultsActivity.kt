package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.adapter.FavorPostAdapter
import com.monitech.favore_app.models.Post
import com.monitech.favore_app.services.PostService

class SearchResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        // Service
        val postService = PostService()

        val favorPostRecycler: RecyclerView = findViewById(R.id.recycler_services)
        val posts: List<Post> = emptyList()

        val txtNoFavorsToShow:TextView = findViewById(R.id.txtNoServices)

        postService.getAllPosts { posts ->
            if (posts!= null) {
                favorPostRecycler.layoutManager = LinearLayoutManager(applicationContext)
                favorPostRecycler.adapter = FavorPostAdapter(posts.reversed())
                favorPostRecycler.adapter = FavorPostAdapter(posts.reversed()).apply {
                    setOnItemClickListener { post ->
                        val intent = Intent(this@SearchResultsActivity, ServiceDetailsActivity::class.java)
                        intent.putExtra("post_id", post.post_id)
                        intent.putExtra("title", post.title)
                        intent.putExtra("description", post.description)
                        intent.putExtra("budgetAmount", post.budgetAmount)

                        // Save user data to shared preferences
                        val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
                        if (sharedPreferences.contains("recentlyViewed")) {
                            val storedPosts = sharedPreferences.getString("recentlyViewed", "")
                            val posts = Gson().fromJson(storedPosts, Array<Post>::class.java).toMutableList()
                            if (posts.size >= 3) {
                                posts.removeAt(0)  // Remove 1 if there are 3 or more
                            }
                            posts.add(post)

                            val newStoredPosts = Gson().toJson(posts)
                            sharedPreferences.edit().putString("recentlyViewed", newStoredPosts).apply()
                        }
                        // If there are no recently viewed posts
                        else {
                            val posts = mutableListOf<Post>()
                            posts.add(post)

                            val newStoredPosts = Gson().toJson(posts)
                            sharedPreferences.edit().putString("recentlyViewed", newStoredPosts).apply()
                        }

                        startActivity(intent)
                    }
                }
            } else {
                txtNoFavorsToShow.text="No favors to show"
            }
        }

        favorPostRecycler.adapter = FavorPostAdapter(posts).apply {

            // This is the code that is executed when the user clicks on a post
            setOnItemClickListener { post ->
                val intent = Intent(this@SearchResultsActivity, ServiceDetailsActivity::class.java)
                intent.putExtra("post_id", post.post_id)
                intent.putExtra("title", post.title)
                intent.putExtra("description", post.description)
                intent.putExtra("budgetAmount", post.budgetAmount)

                startActivity(intent)
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