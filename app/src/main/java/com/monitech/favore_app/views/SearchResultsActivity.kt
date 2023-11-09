package com.monitech.favore_app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
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
            if (posts != null) {
                favorPostRecycler.layoutManager = LinearLayoutManager(applicationContext)
                favorPostRecycler.adapter = FavorPostAdapter(posts)
                favorPostRecycler.adapter = FavorPostAdapter(posts).apply {
                    setOnItemClickListener { post ->
                        val intent = Intent(this@SearchResultsActivity, ServiceDetailsActivity::class.java)
                        intent.putExtra("post_id", post.post_id)
                        intent.putExtra("title", post.title)
                        intent.putExtra("description", post.description)
                        intent.putExtra("budgetAmount", post.budgetAmount)

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
            println("FavorPostAdapter(posts).apply")
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