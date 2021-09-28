package com.icg.training

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.icg.training.ui.BottomNavigationActivity
import kotlinx.android.synthetic.main.app_bar.*

class PracticeNavigationActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_navigation)
        setSupportActionBar(toolbar)
        supportActionBar?.title = this.title
         navController = findNavController(R.id.nav_controller_practice)
        navController.setGraph(R.navigation.nav_graph_practice)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        navController.navigate(R.id.fragmentOne)



    }

    fun sendDataToFragTwo(data:String){
        val bundle = Bundle()
        bundle.putString("data", data)

        navController.navigate(R.id.fragmentTwo,bundle)
    }
    fun sendDataToFragThree(dataFromOne:String,dataFromTwo:String){
        val bundle = Bundle()
        bundle.putString("dataFromOne",dataFromOne)
        bundle.putString("dataFromTwo",dataFromTwo)

        navController.navigate(R.id.fragmentThree,bundle)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
      if (navController.currentDestination?.id == R.id.fragmentOne){

          finish()
      }else{
          navController.popBackStack()
      }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_practice, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_bottom_nav_activity -> {
                startActivity(Intent(this, BottomNavigationActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}