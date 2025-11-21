package com.anondo.shopninja.core

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object Notes {
    const val USER = "User"

    fun encryptData(plainText : String) : String{


        var plainTextByte = plainText.toByteArray(Charsets.UTF_8)

        var password = "7fQ@Lp2!vC9#rXen"
        var passwordByte = password.toByteArray(Charsets.UTF_8)

        var secretKey = SecretKeySpec(passwordByte, "AES")

        var ciper : Cipher = Cipher.getInstance("AES")
        ciper.init(Cipher.ENCRYPT_MODE , secretKey)
        var finalByte = ciper.doFinal(plainTextByte)

        var encodStr = Base64.encodeToString(finalByte , Base64.DEFAULT)

        return encodStr
    }

    fun decryptData(encData : String) : String{

        var decodeData = Base64.decode(encData , Base64.DEFAULT)

        var password = "7fQ@Lp2!vC9#rXen"
        var passwordByte = password.toByteArray(Charsets.UTF_8)

        var secretKey = SecretKeySpec(passwordByte, "AES")

        var ciper : Cipher = Cipher.getInstance("AES")
        ciper.init(Cipher.DECRYPT_MODE , secretKey)
        var finalByte = ciper.doFinal(decodeData)

        var plainDatas = String(finalByte , Charsets.UTF_8)

        return plainDatas
    }

}