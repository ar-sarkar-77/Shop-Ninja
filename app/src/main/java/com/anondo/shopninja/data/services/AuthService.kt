package com.anondo.shopninja.data.services

import com.anondo.shopninja.data.models.UserLogIn
import com.anondo.shopninja.data.models.UserRegistation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthService {

    fun userRegistation(user : UserRegistation) : Task<AuthResult>
    fun userLogin(user : UserLogIn)
    fun createUser (user: UserRegistation)

}