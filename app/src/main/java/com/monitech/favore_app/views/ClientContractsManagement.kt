package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.adapter.ContractAdapter
import com.monitech.favore_app.models.Contract
import com.monitech.favore_app.models.User
import com.monitech.favore_app.services.ContractService
import org.w3c.dom.Text

class ClientContractsManagement : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_contracts_management)
        loadUserAndConfigureNavBar()

        val contractService = ContractService()

        val btnBack: ImageButton = findViewById(R.id.btnBack)


        val contractsRecycler: RecyclerView = findViewById(R.id.recyclerClientContracts)
        val contracts: List<Contract>

        contractService.getAllContracts { contracts ->
            if (contracts != null) {
                val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
                val json = sharedPreferences.getString("user", "")
                val user = Gson().fromJson(json, User::class.java)

                val filteredContracts = contracts.filter { contract -> contract.client?.id == user.id }.reversed()
                val textNoOrder:TextView = findViewById(R.id.txtNoClientOrders)

                if (filteredContracts.isEmpty()) {
                    textNoOrder.isGone = false
                }
                else {
                    textNoOrder.isGone = true
                    contractsRecycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(applicationContext)
                    contractsRecycler.adapter = ContractAdapter(filteredContracts)


                    contractsRecycler.adapter = ContractAdapter(contracts).apply {
//                    setOnItemClickListener { contract ->
////                        val intent = Intent(this@ClientContractsManagement, )
////                        intent.putExtra("post_id", contract.freelancer.name)
////                        intent.putExtra("created_at", contract.createdAt)
////                        intent.putExtra("description", contract.description)
////                        intent.putExtra("ammount", contract.ammount)
////                        startActivity(intent)
//                    }
                    }
                }
            }
        }

        btnBack.setOnClickListener {
            finish()
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