package com.anondo.shopninja

import android.widget.EditText

fun EditText.isEmptyfun() : Boolean{
    return if (this.text.toString().isEmpty()){
        this.error = "This place need to be filled up?"
        true
    }else{
        false
    }
}