package com.anondo.shopninja.data.models

data class UserRegistation(
    var userId : String,
    val userType : String,
    val name : String,
    val email : String,
    val password : String
)