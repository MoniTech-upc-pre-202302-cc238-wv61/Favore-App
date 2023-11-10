package com.monitech.favore_app.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monitech.favore_app.R
import com.monitech.favore_app.models.Contract
import com.squareup.picasso.Picasso

class ContractViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val conctractTitle = view.findViewById<TextView>(R.id.txtContractTitle)
    val image = view.findViewById<ImageView>(R.id.imgContractFreelancerImg)
    val contractFreelancerName = view.findViewById<TextView>(R.id.txtContractFreelancerName)
    val contractPrice = view.findViewById<TextView>(R.id.txtContractPrice)
    val contractDate = view.findViewById<TextView>(R.id.txtContractDateCreated)
    val contractStatus = view.findViewById<TextView>(R.id.txtContractStatus)
    val contractStatusImage = view.findViewById<ImageView>(R.id.imgContractStatus)


    fun render(contractModel: Contract){
        if (contractModel.post != null){
            conctractTitle.text = contractModel.post.title.toString();
        }
        Picasso.get()
            .load(contractModel.freelancer.imageUrl)
            .into(image);
        contractFreelancerName.text = contractModel.freelancer.name.toString() + " " + contractModel.freelancer.lastName.toString();
        contractPrice.text = "USD "+ contractModel.ammount.toString();
        contractModel.createdAt?.let {
            contractDate.text = "Order date " + it.substring(0,10)
        }
        contractStatus.text = contractModel.status.toString();
        if (contractModel.status == "Pending"){
            contractStatusImage.setImageDrawable(itemView.context.getDrawable(R.drawable.pending))
        }else if (contractModel.status == "In progress"){
            contractStatusImage.setImageDrawable(itemView.context.getDrawable(R.drawable.inprogress))
        }
        else if (contractModel.status == "Completed"){
            contractStatusImage.setImageDrawable(itemView.context.getDrawable(R.drawable.completed))
        }
        else if (contractModel.status == "Canceled"){
            contractStatusImage.setImageDrawable(itemView.context.getDrawable(R.drawable.cancelled))
        }
        else {
            contractStatusImage.setImageDrawable(itemView.context.getDrawable(R.drawable.unknown))
        }
    }

}