package com.monitech.favore_app.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monitech.favore_app.R
import com.monitech.favore_app.models.Contract

class ContractViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val contractFreelancerName = view.findViewById<TextView>(R.id.txtContractFreelancerName)
    val contractPrice = view.findViewById<TextView>(R.id.txtContractPrice)
    val contractDate = view.findViewById<TextView>(R.id.txtContractDateCreated)
    val contractStatus = view.findViewById<TextView>(R.id.txtContractStatus)


    fun render(contractModel: Contract){
        contractFreelancerName.text = contractModel.freelancer.name.toString();
        contractPrice.text = "USD "+ contractModel.ammount.toString();
        contractDate.text = contractModel.createdAt.toString();
        contractStatus.text = contractModel.status.toString();
    }

}