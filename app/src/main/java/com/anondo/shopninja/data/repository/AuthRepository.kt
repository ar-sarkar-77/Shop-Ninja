package com.anondo.shopninja.data.repository

import com.anondo.shopninja.core.Notes
import com.anondo.shopninja.data.models.UserLogIn
import com.anondo.shopninja.data.models.UserRegistation
import com.anondo.shopninja.data.services.AuthService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject

class AuthRepository @Inject constructor(
    private val auth : FirebaseAuth,
    private val db : FirebaseFirestore
): AuthService {

    override fun userRegistation(user: UserRegistation) : Task<AuthResult> {

        return auth.createUserWithEmailAndPassword(user.email , user.password)
    }

    override fun userLogin(user : UserLogIn) : Task<AuthResult> {

        return auth.signInWithEmailAndPassword(user.email , user.password)
    }

    override fun createUser(user: UserRegistation) : Task<Void> {

        return db.collection(Notes.USER).document(user.userId).set(user)
    }

}