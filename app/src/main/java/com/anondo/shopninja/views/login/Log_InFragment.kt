package com.anondo.shopninja.views.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anondo.shopninja.R
import com.anondo.shopninja.isEmptyfun
import com.anondo.shopninja.databinding.FragmentLogInBinding


class Log_InFragment : Fragment() {

    lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLogInBinding.inflate(inflater , container , false)
        setLlistener()
        return binding.root
    }

    private fun setLlistener(){

        with(binding){

            buttonLogInLogIn.setOnClickListener {

                emailLogIn.isEmptyfun()
                passwordLogIn.isEmptyfun()

                if (!emailLogIn.isEmptyfun() && !passwordLogIn.isEmptyfun()) {
                    findNavController().navigate(R.id.dashBoardFragment)
                }
            }

            buttonLoginRegistation.setOnClickListener {
                findNavController().navigate(R.id.registerFragment)
            }
        }
    }
}
