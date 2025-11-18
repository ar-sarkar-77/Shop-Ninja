package com.anondo.shopninja.views.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.anondo.shopninja.R
import com.anondo.shopninja.base.BaseFragment
import com.anondo.shopninja.core.DataState
import com.anondo.shopninja.data.models.UserLogIn
import com.anondo.shopninja.databinding.FragmentLogInBinding
import com.anondo.shopninja.isEmptyfun


class Log_InFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {


    private val viewModel : LogInViewModel by viewModels ()

    override fun setListener(){

        with(binding){

            buttonLogInLogIn.setOnClickListener {

                emailLogIn.isEmptyfun()
                passwordLogIn.isEmptyfun()

                if (!emailLogIn.isEmptyfun() && !passwordLogIn.isEmptyfun()) {

                    var user = UserLogIn(emailLogIn.text.toString() , passwordLogIn.text.toString())

                    viewModel.userLogIn(user)

                }
            }

            buttonLoginRegistation.setOnClickListener {
                findNavController().navigate(R.id.registerFragment)
            }
        }
    }

    //OOAD -> Object Oriented Analytics Design (Code Design)
    override fun observer() {

        viewModel.logIn_Responce.observe(viewLifecycleOwner){

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
                    Toast.makeText(context , "Log in success!!", Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.dashBoardFragment)
                }
            }

        }

    }


}
