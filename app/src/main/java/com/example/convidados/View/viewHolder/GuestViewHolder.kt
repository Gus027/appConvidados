package com.example.convidados.View.viewHolder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.Model.GuestModel
import com.example.convidados.View.listener.OnGuestListener
import com.example.convidados.databinding.RowGuestBinding

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel){
        bind.textName.text = guest.name

        bind.textName.setOnClickListener{
                listener.onClick(guest.id)
        }

        bind.textName.setOnLongClickListener{
            AlertDialog.Builder(itemView.context)
                .setTitle("Retirada de convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim"){ dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }

}