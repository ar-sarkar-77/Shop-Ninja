package com.anondo.shopninja.views.starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anondo.shopninja.R
import com.anondo.shopninja.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater , container , false)
        setListener()
        return binding.root
    }

    private fun setListener(){

        with(binding){
            buttonStartLogIn.setOnClickListener {

                findNavController().navigate(R.id.log_InFragment)

            }

            buttonStartRegistation.setOnClickListener {

                findNavController().navigate(R.id.registerFragment)

            }

        }

    }

}