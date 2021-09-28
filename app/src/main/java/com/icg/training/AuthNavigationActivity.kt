package com.icg.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.app_bar.*

class AuthNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_navigation)


        val navController = findNavController(R.id.nav_controller_auth)
        navController.popBackStack()
        navController.navigate(R.id.fragmentLogin)






    }
}