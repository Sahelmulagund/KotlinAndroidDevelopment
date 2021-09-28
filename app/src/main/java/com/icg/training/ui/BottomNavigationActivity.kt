package com.icg.training.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.icg.training.R
import com.icg.training.databinding.ActivityBottomNavigationBinding


class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding
    private var menuClick: Int = -1
    lateinit var navView: BottomNavigationView
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = this.title
         navView = binding.navView

         navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigation)
        navController.popBackStack()
        navController.navigate(R.id.navigation_home)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_orders,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setOnItemSelectedListener { item ->
            item.isChecked = true
            when(item.itemId){
                R.id.navigation_home ->{
                    navController.popBackStack()
                    navController.navigate(R.id.navigation_home)

                }
                R.id.navigation_search ->{
                    navController.popBackStack()
                    navController.navigate(R.id.navigation_search)
                }
                R.id.navigation_orders ->{
                    navController.popBackStack()
                    navController.navigate(R.id.navigation_orders)
                }
                R.id.navigation_profile ->{
                    navController.popBackStack()
                    navController.navigate(R.id.navigation_profile)
                }

            }
            menuClick = item.itemId
            true
        }
        navView.setupWithNavController(navController)
    }
    fun loadNearbyFragment(resId:Int, data:String){
        val bundle = Bundle()
        bundle.putString("data",data)
        navController.navigate(resId, bundle)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.navigation_home){
            finish()
        }else{
            navController.popBackStack()
        }
    }

}