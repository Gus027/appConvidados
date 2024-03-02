package com.example.convidados.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.Model.GuestModel
import com.example.convidados.Repository.GuestRepository

class GuestViewModel(application: Application) : AndroidViewModel(application){

    private var repository = GuestRepository.getInstance(application.applicationContext)

    private val listAllGuest = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuest

    fun getAll(){
        listAllGuest.value = repository.getAll()
    }

    fun getAbsent() {
        listAllGuest.value = repository.getAbsent()
    }

    fun getPresent() {
        listAllGuest.value = repository.getPresence()
    }

            fun delete(id: Int){
        repository.delete(id)
    }
}