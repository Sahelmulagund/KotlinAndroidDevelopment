package com.icg.training.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.icg.training.R
import com.icg.training.data.model.ResultOf
import com.icg.training.presentation.dogsbreed.model.DogsBreedImage
import com.icg.training.presentation.viewmodel.DogVMFactory
import com.icg.training.presentation.viewmodel.dogsViewModel
import com.icg.training.util.launchPeriodically
import com.icg.training.util.showToast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dogs.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job

class DogsAutoLoadActivity : AppCompatActivity() {
    private val dogsVM by viewModels<dogsViewModel>{ DogVMFactory() }
    lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs_auto_load)
//        subscribeToObservers()
//        doFetchApi()
        job = CoroutineScope(Main).launchPeriodically(3000){
            dogsVM.performDelayAction()
            dogsVM.getDogsList()
            subscribeToObservers()

        }

    }

    private fun doFetchApi() {
        dogsVM.getDogsList()
    }


    private fun fetchImages(breedName:String){
        dogsVM.getDogImage(breedName)
    }
    private fun subscribeToObservers() {

        var tempArr:MutableList<String> = ArrayList<String>()
        var tempImgArr:MutableList<DogsBreedImage> = ArrayList<DogsBreedImage>()
        dogsVM.obDogsList.observe(this,{
            when(it){
                is ResultOf.Progress -> showToast(if(it.loading) "Loading.. " else "Loaded")
                is ResultOf.Success -> {



                    for (i in 0..it.value.size){
                        if (tempArr.size < 5 || tempArr.isEmpty()){
                            tempArr.add(it.value.random())

                        }else{
                            break
                        }
                    }


                    tempArr.sortBy { it->
                        it
                    }
                    tempArr.forEach { breedName->
                        fetchImages(breedName)
                    }
                    tv1.setText(tempArr[0])


                    tv2.setText(tempArr[1])


                    tv3.setText(tempArr[2])


                    tv4.setText(tempArr[3])

                    tv5.setText(tempArr[4])



                }
                is ResultOf.Empty -> showToast("No data available!")
                is ResultOf.Failure -> {
                    showToast(it.message!!)
                }
            }
        })

        dogsVM.obDogsImage.observe(this, {
            when(it){
                is ResultOf.Progress -> showToast(if(it.loading) "Loading.. " else "Loaded")
                is ResultOf.Success -> {
                    tempImgArr.add(it.value)
                    if (tempImgArr.isNotEmpty() && tempImgArr.size == 5) {

                        tempImgArr.sortBy { d->
                            d.message
                        }
                        Picasso.get().load(tempImgArr[0].message).into(iv1)
                        Picasso.get().load(tempImgArr[1].message).into(iv2)
                        Picasso.get().load(tempImgArr[2].message).into(iv3)
                        Picasso.get().load(tempImgArr[3].message).into(iv4)
                        Picasso.get().load(tempImgArr[4].message).into(iv5)




                    }

                }
                is ResultOf.Empty -> showToast("No breed available!")
                is ResultOf.Failure -> {
                    showToast(it.message!!)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}