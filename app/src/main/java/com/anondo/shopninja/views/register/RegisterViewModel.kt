package com.anondo.shopninja.views.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anondo.shopninja.core.DataState
import com.anondo.shopninja.data.models.UserRegistation
import com.anondo.shopninja.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userAuth : AuthRepository
): ViewModel() {

    private val registationResponce = MutableLiveData<DataState<UserRegistation>>()

    val registation_Responce : LiveData<DataState<UserRegistation>> = registationResponce

    fun userRegistation(user : UserRegistation){

        registationResponce.postValue(DataState.Loading())


        userAuth.userRegistation(user).addOnSuccessListener {

            it.user?.let{createdUser->
                user.userId = createdUser.uid

                userAuth.createUser(user).addOnSuccessListener {
                    registationResponce.postValue(DataState.Success(user))
                }.addOnFailureListener {
                    registationResponce.postValue(DataState.Error(it.message.toString()))
                }
            }
        }.addOnFailureListener {
            registationResponce.postValue(DataState.Error(it.message.toString()))

        }

    }

}