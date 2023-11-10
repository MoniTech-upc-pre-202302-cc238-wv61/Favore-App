package com.monitech.favore_app.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monitech.favore_app.R
import com.monitech.favore_app.models.Contract
import com.squareup.picasso.Picasso

class ContractViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val image = view.findViewById<ImageView>(R.id.imgFavorPost)
    val contractFreelancerName = view.findViewById<TextView>(R.id.txtContractFreelancerName)
    val contractPrice = view.findViewById<TextView>(R.id.txtContractPrice)
    val contractDate = view.findViewById<TextView>(R.id.txtContractDateCreated)
    val contractStatus = view.findViewById<TextView>(R.id.txtContractStatus)


    fun render(contractModel: Contract){
        Picasso.get()
            .load("https://static.wixstatic.com/media/76751ad539344a41a9950d2ee585e350.jpg/v1/fill/w_560,h_372,al_c,q_80,usm_0.66_1.00_0.01,enc_auto/Contractor.jpg")
            .into(image);
        contractFreelancerName.text = contractModel.freelancer?.name.toString();
        contractPrice.text = "USD "+ contractModel.ammount.toString();
        contractDate.text = contractModel.createdAt.toString();
        contractStatus.text = contractModel.status.toString();
    }

}