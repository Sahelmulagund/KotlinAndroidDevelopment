package com.icg.training

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.icg.training.databinding.FragmentLoginBinding
import com.icg.training.databinding.FragmentTwoBinding


class FragmentTwo : Fragment() {
    private val fragmentThree by lazy {
        FragmentThree()
    }


    private var _binding: FragmentTwoBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTwoBinding.inflate(inflater,container,false)

        initView()
        return binding.root
    }

    private fun initView() {



        val data = arguments?.getString("data", "")
        binding.dataRecv.text = data

        binding.btnSend.setOnClickListener {
            if (binding.edtText.text.toString().isNotEmpty()){
                val dataFromTwo = binding.edtText.text.toString()
                val dataFromOne = binding.dataRecv.text.toString()
                (activity as PracticeNavigationActivity).sendDataToFragThree(dataFromOne,dataFromTwo)

            }else{
                binding.edtText.error = "Please input some data"
                return@setOnClickListener
            }

        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}