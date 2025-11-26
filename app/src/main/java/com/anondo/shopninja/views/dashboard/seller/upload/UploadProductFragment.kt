package com.anondo.shopninja.views.dashboard.seller.upload

import android.Manifest
import android.app.Activity
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.anondo.shopninja.allPermissionGranted
import com.anondo.shopninja.base.BaseFragment
import com.anondo.shopninja.data.models.Product
import com.anondo.shopninja.databinding.FragmentUploadProductBinding
import com.anondo.shopninja.extract
import com.anondo.shopninja.requestPermission
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadProductFragment : BaseFragment<FragmentUploadProductBinding>(FragmentUploadProductBinding::inflate) {

    override fun setListener() {

        permissionRequest = getPermissionRequest()


        binding.apply {

            ivAddProduct.setOnClickListener {
                requestPermission(permissionRequest , permissionList)
            }

            btnUploadProduct.setOnClickListener {

                var name = etProductName.extract()
                var description = etProductDescription.extract()
                var price = etProductPrice.extract().toDouble()
                var quantity = etProductAmount.extract().toInt()

                var product = Product(
                    name =  name,
                    description =  description,
                    price = price,
                    quantity =  quantity
                )

                uploadProduct(product)

            }

        }

    }

    override fun observer() {


    }

    private fun uploadProduct(product: Product) {



    }

    private lateinit var permissionRequest : ActivityResultLauncher<Array<String>>

    companion object{
        private val permissionList = arrayOf(
        //    Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
    }

    private fun getPermissionRequest() : ActivityResultLauncher<Array<String>> {

        return registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){

            if (allPermissionGranted(permissionList)){
                Toast.makeText(requireContext() , "Ache" , Toast.LENGTH_SHORT).show()

                ImagePicker.with(this)
                    .compress(1024)
                    .maxResultSize(512, 512)
                    .createIntent { intent ->
                        startForProfileImageResult.launch(intent)
                    }

            }else{
                Toast.makeText(requireContext() , "Nai" , Toast.LENGTH_SHORT).show()
            }

        }

    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

                binding.ivProduct.setImageURI(fileUri)

            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

}