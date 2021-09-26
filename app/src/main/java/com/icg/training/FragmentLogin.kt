package com.icg.training

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.icg.training.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.edtUsername
import kotlinx.android.synthetic.main.fragment_register.*


class FragmentLogin : Fragment() {

    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private var sharedPreference:SharedPreferences?=null
    private var editor:SharedPreferences.Editor?=null
    private var SHARED_PREFER_NAME = "Reg"

    private var SHARED_PREFER_MODE = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        initView()
        return binding.root

    }

    private fun initView(){
        binding.signupTxt.setOnClickListener {
            (activity as AuthActivity).addReplaceFragment(RegisterFragment(), 2, "fragmentLogin")
        }
        sharedPreference = activity?.getSharedPreferences(SHARED_PREFER_NAME, SHARED_PREFER_MODE)


        binding.loginBtn.setOnClickListener {
            if (binding.edtUsername.text.toString().trim().isEmpty()){
                binding.edtUsername.error = "User name cannot be empty!"
                return@setOnClickListener
            }
            if (binding.edtUsername.text.toString().trim().isEmpty()){
                binding.edtPassword.error = "Password cannot be empty!"
                return@setOnClickListener
            }
            val userName = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            if (sharedPreference!!.contains("username") && sharedPreference!!.contains("password"))
            {
                val uName = sharedPreference!!.getString("username", "")
                val uPassword = sharedPreference!!.getString("password","")
                if (!userName.equals(uName)){
                    Snackbar.make(binding.mainFrag, "User not registered", Snackbar.LENGTH_SHORT).show()
                     return@setOnClickListener
                }else if (!password.equals(uPassword)){
                    Snackbar.make(binding.mainFrag, "Invalid user credentials",Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }else{
                    Snackbar.make(binding.mainFrag, "Logged in successfully", Snackbar.LENGTH_SHORT).show()
                    startActivity(Intent(context, MainActivity::class.java))
                    activity?.finish()

                }
            }
        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}