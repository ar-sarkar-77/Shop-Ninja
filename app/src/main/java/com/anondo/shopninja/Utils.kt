package com.anondo.shopninja

import android.content.pm.PackageManager
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun EditText.isEmptyfun() : Boolean{
    return if (this.text.toString().isEmpty()){
        this.error = "This place need to be filled up?"
        true
    }else{
        false
    }
}

fun EditText.extract(): String{
    return text.toString().trim()
}

fun Fragment.requestPermission(request : ActivityResultLauncher<Array<String>>, permissions : Array<String>){

    request.launch(permissions)

}

fun Fragment.allPermissionGranted(permissions: Array<String>): Boolean{

    return permissions.all {
        ContextCompat.checkSelfPermission(requireContext() , it)== PackageManager.PERMISSION_GRANTED
    }

}