package com.icg.training.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.icg.training.R
import com.icg.training.data.model.ResultOf
import com.icg.training.presentation.dogsbreed.model.DogsBreedImage
import com.icg.training.presentation.viewmodel.DogVMFactory
import com.icg.training.presentation.viewmodel.dogsViewModel
import com.icg.training.util.GlideApp
import com.icg.training.util.launchPeriodically
import com.icg.training.util.showToast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dogs.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job

class DogsAutoLoadActivity : AppCompatActivity() {
    private val dogsVM by viewModels<dogsViewModel>{ DogVMFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs_auto_load)
//        subscribeToObservers()
//        doFetchApi()
        dogsVM.getDogListAutoReload()
        subscribeToObservers()
//        job = CoroutineScope(Main).launchPeriodically(3000){
//            dogsVM.performDelayAction()
//
//
//        }

    }





    private fun subscribeToObservers() {


        dogsVM.obDogsList.observe(this,{
            when(it){
                is ResultOf.Progress -> showToast(if(it.loading) "Loading.. " else "Loaded")
                is ResultOf.Success -> {
                    it?.let {
                        it.value[0].let {
                            tv1.text = it.breed
                            it.imageUrl?.let { it1-> GlideApp.with(this).load(it1).into(iv1) }
                        }
                        it.value[1].let {
                            tv2.text = it.breed
                            it.imageUrl?.let { it1-> GlideApp.with(this).load(it1).into(iv2) }
                        }
                        it.value[2].let {
                            tv3.text = it.breed
                            it.imageUrl?.let { it1-> GlideApp.with(this).load(it1).into(iv3) }
                        }
                        it.value[3].let {
                            tv4.text = it.breed
                            it.imageUrl?.let { it1-> GlideApp.with(this).load(it1).into(iv4) }
                        }
                        it.value[4].let {
                            tv5.text = it.breed
                            it.imageUrl?.let { it1-> GlideApp.with(this).load(it1).into(iv5) }
                        }
                    }


                }
                is ResultOf.Empty -> showToast("No data available!")
                is ResultOf.Failure -> {
                    showToast(it.message!!)
                }
            }
        })


    }



}