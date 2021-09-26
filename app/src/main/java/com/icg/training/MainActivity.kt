package com.icg.training

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.icg.training.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding
    private var menuItemClick = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        findNavController(R.id.nav_host_fragment_content_main).popBackStack()
        navController.navigate(R.id.action_recycler_items)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_recycler_items -> {
                findNavController(R.id.nav_host_fragment_content_main).popBackStack()
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_recycler_items)

                true
            }
            R.id.action_list_items -> {

                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.SecondFragment)

                true
            }
            R.id.action_widgets -> {

                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.ThirdFragment)

                true
            }
            R.id.action_dialog-> {

                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.DialogFragment)

                true
            }
            R.id.action_sample_fragment->{
                startActivity(Intent(this, FragmentSampleActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}