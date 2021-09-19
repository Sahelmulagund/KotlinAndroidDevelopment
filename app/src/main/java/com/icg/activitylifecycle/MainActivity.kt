package com.icg.activitylifecycle


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Activity_Lifecycle", "onCreate invoked")
        Toast.makeText(this@MainActivity, "onCreate invoked", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Activity_Lifecycle", "onStart invoked")
        Toast.makeText(this@MainActivity, "Start", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Activity_Lifecycle", "onResume invoked")
        Toast.makeText(this@MainActivity, "Resume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("Activity_Lifecycle", "onPause invoked")
        Toast.makeText(this@MainActivity, "Pause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("Activity_Lifecycle", "onStop invoked")
        Toast.makeText(this@MainActivity, "Stop", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Activity_Lifecycle", "onRestart invoked")
        Toast.makeText(this@MainActivity, "Restart", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Activity_Lifecycle", "onDestroy invoked")
        Toast.makeText(this@MainActivity, "Destroy", Toast.LENGTH_SHORT).show()
    }
}