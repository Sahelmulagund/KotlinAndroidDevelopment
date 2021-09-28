package com.icg.training.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icg.training.R
import com.icg.training.databinding.FragmentSearch2Binding


class SearchFragment : Fragment() {

    private var _binding:FragmentSearch2Binding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearch2Binding.inflate(inflater,container,false)
        if (arguments != null){
            val dataRcv = arguments?.getString("data","")
            binding.dataRecv.text = dataRcv
        }
        binding.btnSend.setOnClickListener {
            val data = binding.tietData.text.toString()
            (activity as BottomNavigationActivity).loadNearbyFragment(R.id.navigation_orders,data)
        }
        return binding.root
    }

}