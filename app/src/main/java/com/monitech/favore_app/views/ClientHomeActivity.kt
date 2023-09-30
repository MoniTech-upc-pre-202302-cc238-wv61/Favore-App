package com.monitech.favore_app.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.monitech.favore_app.R
import com.google.android.material.search.SearchBar

class ClientHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)

        val searchBar = findViewById<SearchBar>(R.id.searchBar)

        searchBar.setOnClickListener {
            val intent = Intent(this, SearchServiceActivity::class.java)
            startActivity(intent)
        }
    }
}
