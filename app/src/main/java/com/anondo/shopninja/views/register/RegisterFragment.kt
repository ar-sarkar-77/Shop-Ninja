package com.anondo.shopninja.views.register

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.anondo.shopninja.R
import com.anondo.shopninja.base.BaseFragment
import com.anondo.shopninja.core.DataState
import com.anondo.shopninja.data.models.UserRegistation
import com.anondo.shopninja.databinding.FragmentRegisterBinding
import com.anondo.shopninja.isEmptyfun
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val viewModel : RegisterViewModel by viewModels ()
    private var userType : String = ""

    override fun setListener(){



        with(binding){

            ragisterToggleButton.addOnButtonCheckedListener { group , checkId , isChecked ->

                if (isChecked){
                    toggleError.visibility = View.GONE
                    when(checkId){
                        R.id.ragister_Buyer->{
                            userType = "Buyer"
                        }
                        R.id.ragister_Seller->{
                            userType = "Seller"
                        }
                    }
                }

            }

            buttonRegisterLogIn.setOnClickListener {

                findNavController().navigate(R.id.log_InFragment)

            }

            buttonRegisterRegistation.setOnClickListener {

                nameRegister.isEmptyfun()
                emailRegister.isEmptyfun()
                passwordRegister.isEmptyfun()

                if (userType.isEmpty()){
                    toggleError.visibility = View.VISIBLE
                }else{
                    toggleError.visibility = View.GONE
                }


                if (!nameRegister.isEmptyfun() && !emailRegister.isEmptyfun() && !passwordRegister.isEmptyfun() && !userType.isEmpty()){

                    var user = UserRegistation("" , userType , nameRegister.text.toString() , emailRegister.text.toString() , passwordRegister.text.toString())

                    viewModel.userRegistation(user)

                }

            }
        }

    }

    //OOAD -> Object Oriented Analytics Design (Code Design)
    override fun observer() {

        viewModel.registation_Responce.observe(viewLifecycleOwner){

            when(it){
                is DataState.Error -> {
                    loading_progress_dialog.dismiss()
                    Toast.makeText(context , it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    loading_progress_dialog.show()
                }
                is DataState.Success -> {
                    loading_progress_dialog.dismiss()
                    Toast.makeText(context , "Registation Success!!", Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.dashBoardFragment)
                }
            }

        }

    }

}