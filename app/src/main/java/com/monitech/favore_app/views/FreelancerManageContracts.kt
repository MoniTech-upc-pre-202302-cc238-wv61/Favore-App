package com.monitech.favore_app.views

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.adapter.ContractAdapter
import com.monitech.favore_app.models.Contract
import com.monitech.favore_app.models.User
import com.monitech.favore_app.services.ContractService

class FreelancerManageContracts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freelancer_manage_contracts)

        val contractService = ContractService()

        val contractsRecycler: RecyclerView = findViewById(R.id.recyclerFreelancerContracts)
        val contracts: List<Contract>

        val btnBack:ImageButton = findViewById(R.id.btnBack)
        btnBack.setOnClickListener(){
            finish()
        }

        contractService.getAllContracts { contracts ->
            if (contracts != null) {
                val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
                val json = sharedPreferences.getString("user", "")
                val user = Gson().fromJson(json, User::class.java)

                val filteredContracts = contracts.filter { contract -> contract.freelancer.id == user.id }.reversed()
                val textNoOrder: TextView = findViewById(R.id.txtNoFreelancerOrders)

                if (filteredContracts.isEmpty()) {
                    textNoOrder.isGone = false
                }
                else {
                    textNoOrder.isGone = true

                    contractsRecycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(applicationContext)
                    contractsRecycler.adapter = ContractAdapter(filteredContracts)

                    contractsRecycler.adapter = ContractAdapter(filteredContracts).apply {
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
    }
}