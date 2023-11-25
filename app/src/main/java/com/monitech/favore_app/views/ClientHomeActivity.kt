package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.monitech.favore_app.R
import com.google.android.material.search.SearchBar
import com.google.gson.Gson
import com.monitech.favore_app.adapter.FavorPostAdapter
import com.monitech.favore_app.models.Post
import com.monitech.favore_app.models.User
import com.monitech.favore_app.services.PostService
import com.squareup.picasso.Picasso

class ClientHomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)

        val sharedPreferences = getSharedPreferences("favore", MODE_PRIVATE)
        val json = sharedPreferences.getString("user", "")
        val user = Gson().fromJson(json, User::class.java)
        configureNavBar(user.type)

        val searchBar = findViewById<SearchBar>(R.id.searchBar)

        searchBar.setOnClickListener {
            val intent = Intent(this, SearchServiceActivity::class.java)
            startActivity(intent)
        }

        val txtHello = findViewById<TextView>(R.id.txtHelloClient)

        txtHello.text = "Hello, ${user.name}!"

        val imgMoving:ImageView = findViewById(R.id.imgMoving)
        val goMoving:LinearLayout = findViewById(R.id.goMoving)
        Picasso.get()
            .load("https://images.pexels.com/photos/4061977/pexels-photo-4061977.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
            .into(imgMoving);
        goMoving.setOnClickListener(){
            val intent = Intent(this, SearchResultsActivity::class.java)
            startActivity(intent)
        }

        val imgTutoring:ImageView = findViewById(R.id.imgTutoring)
        val goTutoring:LinearLayout = findViewById(R.id.goTutoring)
        Picasso.get()
            .load("https://images.pexels.com/photos/8617769/pexels-photo-8617769.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
            .into(imgTutoring);
        goTutoring.setOnClickListener(){
            val intent = Intent(this, SearchResultsActivity::class.java)
            startActivity(intent)
        }

        val imgCleaning:ImageView = findViewById(R.id.imgCleaning)
        val goCleaning:LinearLayout = findViewById(R.id.goCleaning)
        Picasso.get()
            .load("https://images.pexels.com/photos/5556176/pexels-photo-5556176.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
            .into(imgCleaning);
        goCleaning.setOnClickListener(){
            val intent = Intent(this, SearchResultsActivity::class.java)
            startActivity(intent)
        }


        if (sharedPreferences.contains("recentlyViewed")) {
            val json = sharedPreferences.getString("recentlyViewed", "")
            val posts = Gson().fromJson(json, Array<Post>::class.java).toList()
            if (posts.isEmpty()) {
                val txtNoFavorsToShow:TextView = findViewById(R.id.txtNoRecentlyViewed)
                txtNoFavorsToShow.isGone = false
            }
            else {
                val recentlyViewed: RecyclerView = findViewById(R.id.recycler_recentlyViewed)
                recentlyViewed.layoutManager = LinearLayoutManager(applicationContext)
                recentlyViewed.adapter = FavorPostAdapter(posts.reversed())
                recentlyViewed.adapter = FavorPostAdapter(posts).apply {
                    setOnItemClickListener { post ->
                        val intent = Intent(this@ClientHomeActivity, ServiceDetailsActivity::class.java)
                        println(post.post_id)
                        startActivity(intent)
                    }
                }
            }
        } else {
            val txtNoFavorsToShow:TextView = findViewById(R.id.txtNoRecentlyViewed)
            txtNoFavorsToShow.isGone = false
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
//                    startActivity(Intent(this, FreelancerFavorsManagementActivity::class.java))
                    startActivity(Intent(this, ClientContractsManagement::class.java))
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
