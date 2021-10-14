package com.icg.training.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.icg.training.R
import com.icg.training.adapter.BreedAdapter
import com.icg.training.data.model.ResultOf
import com.icg.training.presentation.dogsbreed.model.DogsBreedImage
import com.icg.training.presentation.viewmodel.DogVMFactory
import com.icg.training.presentation.viewmodel.dogsViewModel
import com.icg.training.util.showToast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dogs.*
import kotlinx.android.synthetic.main.activity_dogs_breed_image.*
import kotlinx.android.synthetic.main.activity_dogs_breed_image.toolbar

class DogsBreedImageActivity : AppCompatActivity() {
    private val dogsVM by viewModels<dogsViewModel>{ DogVMFactory() }
   private val adapter by lazy(LazyThreadSafetyMode.NONE) {BreedAdapter()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs_breed_image)
        setSupportActionBar(toolbar)
        toolbar.title = this.title


        doFetchApi()
        subscribeToObservers()
        rvBreedImage.adapter = adapter
        rvBreedImage.apply {

            layoutManager = GridLayoutManager(applicationContext,2)
            setHasFixedSize(true)
        }

    }



    private fun doFetchApi() {
        dogsVM.getDogListSynchronously()
    }

    private fun subscribeToObservers() {


        dogsVM.obDogsList.observe(this,{
            when(it){
                is ResultOf.Progress -> showToast(if(it.loading) "Loading.. " else "Loaded")
                is ResultOf.Success -> {
                    adapter.breed = it.value
                }
                is ResultOf.Empty -> showToast("No data available!")
                is ResultOf.Failure -> {
                    showToast(it.message!!)
                }
            }
        })


    }

}