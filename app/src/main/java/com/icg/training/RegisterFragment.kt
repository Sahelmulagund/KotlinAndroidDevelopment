package com.icg.training

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.icg.training.databinding.FragmentLoginBinding
import com.icg.training.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    private var sharedPreference: SharedPreferences?=null
    private var editor: SharedPreferences.Editor?=null
    private var SHARED_PREFER_NAME = "Reg"
    private var SHARED_PREFER_MODE = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        initView()
        return binding.root
    }

    private fun initView() {
        sharedPreference = activity?.applicationContext?.getSharedPreferences(SHARED_PREFER_NAME, SHARED_PREFER_MODE)

        editor = sharedPreference?.edit()

        binding.registerBtn.setOnClickListener {
            if (binding.edtUsername.text.toString().trim().isEmpty()){
                binding.edtUsername.error = "User name is required!"
                return@setOnClickListener
            }
            if (binding.edtPass.text.toString().trim().isEmpty()){
                binding.edtPass.error = "Password is required!"
                return@setOnClickListener

            }
            if (binding.edtConfirmPass.text.toString().trim().isEmpty()){
                binding.edtConfirmPass.error = "Please confirm the password"
                return@setOnClickListener
            }
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPass.text.toString().trim()
            val confirmPass = binding.edtConfirmPass.text.toString().trim()

            editor?.putString("username", username)
            editor?.putString("password", password)
            editor?.putString("confirmPassword", confirmPass)
            
            editor?.apply()
            activity?.supportFragmentManager?.popBackStack()
            Snackbar.make(binding.regFrag, "Registered successfully! Please login",Snackbar.LENGTH_SHORT).show()


        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}