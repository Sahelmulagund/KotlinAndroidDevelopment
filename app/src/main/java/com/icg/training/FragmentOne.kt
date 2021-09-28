package com.icg.training

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.icg.training.databinding.FragmentOneBinding
import kotlinx.android.synthetic.main.fragment_one.*


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



        initView()
        return binding.root
    }
    private fun initView() {


        binding.btnSend.setOnClickListener {
                if (edtText.text.toString().isEmpty()){
                    edtText.error = "Please enter some data"
                    return@setOnClickListener
                }
                val data = binding.edtText.text.toString()

            (activity as PracticeNavigationActivity).sendDataToFragTwo(data)
//

//            (activity as FragmentSampleActivity).sendDataToFragTwo(fragmentTwo, data)

        }




    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }

}