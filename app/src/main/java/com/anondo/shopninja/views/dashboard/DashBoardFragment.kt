package com.anondo.shopninja.views.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anondo.shopninja.R
import com.anondo.shopninja.databinding.FragmentDashBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardFragment : Fragment() {

    lateinit var binding : FragmentDashBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashBoardBinding.inflate(inflater , container , false)
        setListener()
        return binding.root
    }

    private fun setListener() {

        with(binding){

            dashAnimationView.postDelayed({
                dashAnimationView.visibility = View.GONE
                dashBoardMain.visibility = View.VISIBLE
            } , 4000)

        }

    }

}