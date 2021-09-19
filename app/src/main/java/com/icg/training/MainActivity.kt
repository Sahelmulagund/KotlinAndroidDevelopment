package com.icg.training

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
    private lateinit var binding: ActivityMainBinding

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

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
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
                findNavController(R.id.nav_host_fragment_content_main).popBackStack()
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.SecondFragment)

                true
            }
            R.id.action_widgets -> {
                findNavController(R.id.nav_host_fragment_content_main).popBackStack()
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.ThirdFragment)

                true
            }
            R.id.action_dialog-> {
                findNavController(R.id.nav_host_fragment_content_main).popBackStack()
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.DialogFragment)

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