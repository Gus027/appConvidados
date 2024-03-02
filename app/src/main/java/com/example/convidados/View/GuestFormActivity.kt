package com.example.convidados.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.Model.GuestModel
import com.example.convidados.R
import com.example.convidados.ViewModel.GuestFormViewModel
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel


    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel  = ViewModelProvider(this).get(GuestFormViewModel::class.java)
        binding.buttonSave.setOnClickListener(this)

        observe()
        loadData()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_save)
        {
            val name = binding.editName.text.toString()
            var presence = false

            if(binding.radioAbsent.isChecked==false)
            {
               presence = true
            }

            val model = GuestModel(guestId,name,presence)
            viewModel.save(model)

            // TODO Temp
            finish()
        }
    }

    private fun observe(){
        viewModel.guests.observe(this,{
            binding.editName.setText(it.name)
            if(it.presence){
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        })
    }

    private fun loadData(){
        val bundle = intent.extras
        if(bundle != null){
            guestId =  bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }

}