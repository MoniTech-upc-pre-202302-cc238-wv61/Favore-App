package com.monitech.favore_app.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monitech.favore_app.R
import com.monitech.favore_app.models.Post

class FavorPostViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val favorTitle = view.findViewById<TextView>(R.id.txtFavorPostTitle)
    val favorDescription = view.findViewById<TextView>(R.id.txtFavorPostDescription)
    val favorFreelancerName = view.findViewById<TextView>(R.id.txtFavorPostFreelancerName)
    val favorPrice = view.findViewById<TextView>(R.id.txtFavorPostPrice)

    fun render(postModel: Post){
        favorTitle.text = postModel.title.toString();
        favorDescription.text = postModel.description.toString();
        favorPrice.text = "From USD "+ postModel.budgetAmount.toString() + " per session";
        favorFreelancerName.text = "TODO"
    }

}