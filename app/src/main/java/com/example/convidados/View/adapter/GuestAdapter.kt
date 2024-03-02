package com.example.convidados.View.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.Model.GuestModel
import com.example.convidados.View.listener.OnGuestListener
import com.example.convidados.View.viewHolder.GuestViewHolder
import com.example.convidados.databinding.RowGuestBinding

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var guesList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
       val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return GuestViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guesList[position])
    }

    override fun getItemCount(): Int {
        return guesList.count()
    }

    fun UpdatedGuests(list: List<GuestModel>){
        guesList = list
        notifyDataSetChanged()
    }

    fun attachListener(guestListener: OnGuestListener){
        listener = guestListener
    }
}