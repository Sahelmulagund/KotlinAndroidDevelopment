package com.icg.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.icg.training.databinding.FragmentThreeBinding


class FragmentThree : Fragment() {

    private var _binding:FragmentThreeBinding?=null
    private val binding  get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThreeBinding.inflate(inflater,container,false)
        initView()
        return binding.root
    }

    private fun initView(){

        val dataFromOne = arguments?.getString("dataFromOne", "")
        val dataFromTwo = arguments?.getString("dataFromTwo","")

        binding.tvDataFromOne.text = dataFromOne
        binding.tvDataFromTwo.text = dataFromTwo
        binding.btnFinish.setOnClickListener {
            (activity as PracticeNavigationActivity).findNavController(R.id.nav_controller_practice).navigate(R.id.fragmentOne)

        }

    }
}