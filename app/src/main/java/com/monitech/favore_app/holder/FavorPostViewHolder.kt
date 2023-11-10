package com.monitech.favore_app.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monitech.favore_app.R
import com.monitech.favore_app.models.Post
import com.squareup.picasso.Picasso

class FavorPostViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val image = view.findViewById<ImageView>(R.id.imgFavorPost)
    val profilePictureImage = view.findViewById<ImageView>(R.id.imgFavorPostFreelancerImg)
    val favorTitle = view.findViewById<TextView>(R.id.txtFavorPostTitle)
    val favorDescription = view.findViewById<TextView>(R.id.txtFavorPostDescription)
    val favorFreelancerName = view.findViewById<TextView>(R.id.txtFavorPostFreelancerName)
    val favorPrice = view.findViewById<TextView>(R.id.txtFavorPostPrice)

    fun render(postModel: Post){
        Picasso.get()
            .load("https://static.wixstatic.com/media/76751ad539344a41a9950d2ee585e350.jpg/v1/fill/w_560,h_372,al_c,q_80,usm_0.66_1.00_0.01,enc_auto/Contractor.jpg")
            .into(image);
        Picasso.get()
            .load(postModel.user.imageUrl)
            .into(profilePictureImage);
        favorTitle.text = postModel.title.toString();
        favorDescription.text = postModel.description.toString();
        favorPrice.text = "From USD "+ postModel.budgetAmount.toString() + "/session";
        favorFreelancerName.text = postModel.user.name.toString() + " " + postModel.user.lastName.toString();
    }

}