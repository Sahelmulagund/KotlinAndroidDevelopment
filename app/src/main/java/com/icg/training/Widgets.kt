package com.icg.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Toast
import com.icg.training.databinding.FragmentFirstBinding
import com.icg.training.databinding.FragmentWidgetsBinding


class Widgets : Fragment() {
    private var _binding: FragmentWidgetsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWidgetsBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) Toast.makeText(context, "Is Checked", Toast.LENGTH_SHORT).show()
            else Toast.makeText(context, "Not Checked", Toast.LENGTH_SHORT).show()
        }
        binding.tb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) Toast.makeText(context, "On", Toast.LENGTH_SHORT).show()
            else Toast.makeText(context, "Off", Toast.LENGTH_SHORT).show()
        }
        binding.switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) Toast.makeText(context, "Switch On", Toast.LENGTH_SHORT).show()
            else Toast.makeText(context, "Switch off", Toast.LENGTH_SHORT).show()
        }
        binding.mtBtn1.setOnClickListener {
            Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show()
        }
        binding.clickable.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) Toast.makeText(context, "Is Checked", Toast.LENGTH_SHORT).show()
            else Toast.makeText(context, "Not Checked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}