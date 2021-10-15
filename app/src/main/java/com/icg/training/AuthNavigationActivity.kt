package com.icg.training

import android.R.attr.key
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController


class AuthNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_navigation)


//            val extras = intent.extras

            val data = intent.extras?.getString("data", "notOpen")
            if (data.toString() != "notOpen" && data != null){

                val c = Class.forName(data)
                startActivity(Intent(this,c))
            }else{
                Log.v("intentNoValue", "intent No Value")
            }



        val navController = findNavController(R.id.nav_controller_auth)
        navController.popBackStack()
        navController.navigate(R.id.fragmentLogin)






    }


}