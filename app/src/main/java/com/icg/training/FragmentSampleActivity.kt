package com.icg.training

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.app_bar.*

class FragmentSampleActivity : AppCompatActivity() {
    var dataBetweenFragment:Bundle?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_sample)
        setSupportActionBar(toolbar)

        toolbar.title = "Training"
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragOne:Fragment =FragmentOne()
        val fragTwo:Fragment = FragmentTwo()
       transaction.add(R.id.fragOne,fragOne)
        transaction.add(R.id.fragTwo,fragTwo)


        transaction.commit()


    }

    fun sendDataToFragTwo(fragment: Fragment, data: String?){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("data", data)
        fragment.arguments = bundle
        transaction.replace(R.id.fragTwo, fragment)
        transaction.commitAllowingStateLoss()
    }
    fun sendDataToFragOne(fragment: Fragment, data: String?){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("data", data)
        fragment.arguments = bundle
        transaction.replace(R.id.fragOne, fragment)

        transaction.commitAllowingStateLoss()
    }
    fun saveData(data: Bundle?) {
        dataBetweenFragment = data

    }

    fun getSavedData(): Bundle? {

        return dataBetweenFragment
    }
}