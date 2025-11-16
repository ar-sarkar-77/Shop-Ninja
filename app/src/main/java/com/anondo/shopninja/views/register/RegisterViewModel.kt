package com.anondo.shopninja.views.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anondo.shopninja.core.DataState
import com.anondo.shopninja.data.models.UserRegistation
import com.anondo.shopninja.data.repository.AuthRepository

class RegisterViewModel : ViewModel() {

    private val registationResponce = MutableLiveData<DataState<UserRegistation>>()

    val registation_Responce : LiveData<DataState<UserRegistation>> = registationResponce

    fun userRegistation(user : UserRegistation){

        var userAuth = AuthRepository()

        registationResponce.postValue(DataState.Loading())


        userAuth.userRegistation(user).addOnSuccessListener {
            registationResponce.postValue(DataState.Success(user))
        }.addOnFailureListener {
            registationResponce.postValue(DataState.Error(it.message.toString()))

        }

    }

}