package com.anondo.shopninja.views.login

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
import com.anondo.shopninja.data.models.UserLogIn
import com.anondo.shopninja.isEmptyfun
import com.anondo.shopninja.databinding.FragmentLogInBinding


class Log_InFragment : Fragment() {

    lateinit var binding: FragmentLogInBinding
    private val viewModel : LogInViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLogInBinding.inflate(inflater , container , false)
        setLlistener()
        loginResponce()
        return binding.root
    }

    private fun setLlistener(){

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

    private fun loginResponce() {

        viewModel.logIn_Responce.observe(viewLifecycleOwner){

            when(it){
                is DataState.Error -> {
                    Toast.makeText(context , it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {

                }
                is DataState.Success -> {
                    Toast.makeText(context , "Log in success!!", Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.dashBoardFragment)
                }
            }

        }

    }


}
