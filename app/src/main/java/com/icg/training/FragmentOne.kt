package com.icg.training

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.icg.training.databinding.FragmentOneBinding


class FragmentOne : Fragment() {

    private var _binding: FragmentOneBinding?=null
    private val binding get() = _binding!!
    private val fragmentTwo by lazy {
        FragmentTwo()
    }
    val args = Bundle()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(inflater,container,false)

        if (arguments != null){
            binding.dataRecv.setText(arguments?.getString("data",""))
        }

        initView()
        return binding.root
    }
    private fun initView() {


        binding.btnSend.setOnClickListener {

                val data = binding.edtText.text.toString()

//
                args?.putString("dataFromOne", data)
            (activity as FragmentSampleActivity).sendDataToFragTwo(fragmentTwo, data)


        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}