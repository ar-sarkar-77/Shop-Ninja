package com.anondo.shopninja.data.repository

import com.anondo.shopninja.data.models.UserLogIn
import com.anondo.shopninja.data.models.UserRegistation
import com.anondo.shopninja.data.services.AuthService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthRepository: AuthService {

    override fun userRegistation(user: UserRegistation) : Task<AuthResult> {

        var fireBase_Reg_Auth = FirebaseAuth.getInstance()

        return fireBase_Reg_Auth.createUserWithEmailAndPassword(user.email , user.password)
    }

    override fun userLogin(user: UserLogIn) {

    }

    override fun createUser(user: UserRegistation) {

    }

}