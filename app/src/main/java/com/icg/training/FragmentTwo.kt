package com.icg.training

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icg.training.databinding.FragmentLoginBinding
import com.icg.training.databinding.FragmentTwoBinding


class FragmentTwo : Fragment() {
    private val fragmentOne by lazy {
        FragmentOne()
    }


    private var _binding: FragmentTwoBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTwoBinding.inflate(inflater,container,false)
        val args = this.arguments
        if (args != null){
            val dataRecv = args
            if (dataRecv != null){

                binding.dataRecv.text = dataRecv.getString("data", "")

            }
        }

        initView()
        return binding.root
    }

    private fun initView() {


        val args = Bundle()
        binding.btnSend.setOnClickListener {
            if (binding.edtText.text.toString().isNotEmpty()){
                val data = binding.edtText.text.toString()

                (activity as FragmentSampleActivity).sendDataToFragOne(fragmentOne, data)

            }

        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}