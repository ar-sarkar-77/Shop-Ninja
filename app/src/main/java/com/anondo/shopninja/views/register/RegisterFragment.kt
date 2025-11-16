package com.anondo.shopninja.views.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.anondo.shopninja.R
import com.anondo.shopninja.core.DataState
import com.anondo.shopninja.data.models.UserRegistation
import com.anondo.shopninja.databinding.FragmentRegisterBinding
import com.anondo.shopninja.isEmptyfun

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    private val viewModel : RegisterViewModel by viewModels ()
    private var userType : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(inflater , container , false)

        setRListener()

        registationResponce()

        return binding.root
    }



    private fun setRListener(){



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

    private fun registationResponce() {

        viewModel.registation_Responce.observe(viewLifecycleOwner){

            when(it){
                is DataState.Error -> {
                    Toast.makeText(context , it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    Toast.makeText(context , "Loading", Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    Toast.makeText(context , it.data.toString(), Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.dashBoardFragment)
                }
            }

        }

    }

}