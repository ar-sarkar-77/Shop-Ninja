package com.anondo.shopninja.data.models

data class Product(
    var name : String = "",
    var description : String = "",
    var imageLink : String = "",
    var price : Double = 0.0,
    var quantity : Int = 0,
    var sellerId : String = "",
    var productId : String = ""
)
