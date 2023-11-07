package com.monitech.favore_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monitech.favore_app.R
import com.monitech.favore_app.holder.ContractViewHolder
import com.monitech.favore_app.models.Contract

class ContractAdapter(val listContract: List<Contract>): RecyclerView.Adapter<ContractViewHolder>() {
    private var onItemClickListener: ((Contract) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContractViewHolder(layoutInflater.inflate(R.layout.contract_card, parent, false))
    }

    override fun getItemCount(): Int = listContract.size
    override fun onBindViewHolder(holder: ContractViewHolder, position: Int) {
        val item = listContract[position]
        holder.render(item)

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }

    fun setOnItemClickListener(listener: (Contract) -> Unit) {
        onItemClickListener = listener
    }
}