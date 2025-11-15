package com.anondo.shopninja.views.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anondo.shopninja.R
import com.anondo.shopninja.databinding.FragmentRegisterBinding
import com.anondo.shopninja.databinding.FragmentStartBinding

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(inflater , container , false)

        setRListener()

        return binding.root
    }

    private fun setRListener(){

        with(binding){
            buttonRegisterLogIn.setOnClickListener {

                findNavController().navigate(R.id.log_InFragment)

            }

            buttonRegisterRegistation.setOnClickListener {

                findNavController().navigate(R.id.dashBoardFragment)

            }
        }

    }

}