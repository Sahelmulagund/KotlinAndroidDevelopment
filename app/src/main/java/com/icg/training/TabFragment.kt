package com.icg.training

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icg.training.databinding.FragmentTabBinding

class TabFragment : Fragment() {
    private var _binding:FragmentTabBinding?=null
     val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentTabBinding.inflate(inflater,container,false)
        val data = arguments?.getString(Constants.Keys.data)
        binding.tvText.text = data
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}