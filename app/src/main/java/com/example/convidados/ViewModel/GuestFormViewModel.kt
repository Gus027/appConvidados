package com.example.convidados.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.Model.GuestModel
import com.example.convidados.Repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    /// "Application" - É a referencia da classe, para adicionar um contexto no AndroidViewModel
    /// "AndroidViewModel - É utilizado para criação de algum contexto.

    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guests: LiveData<GuestModel> = guestModel

    fun save(guest: GuestModel){
        if(guest.id==0){
            repository.insert(guest)
        } else {
            repository.update(guest)
        }


    }

    fun get(id: Int){
       guestModel.value = repository.get(id)
    }
}