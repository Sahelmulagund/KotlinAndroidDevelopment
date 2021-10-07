package com.icg.training

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.icg.training.adapter.PhotosAdapter
import com.icg.training.databinding.ActivityPhotosBinding
import com.icg.training.result.Result
import com.icg.training.viewmodelfactory.ViewModelFactory
import com.icg.training.viewmodels.PhotosViewModel

class PhotosActivity : AppCompatActivity() {
   private lateinit var binding:ActivityPhotosBinding

    lateinit var adapter:PhotosAdapter
//    private  var photoViewModelFactory: ViewModelFactory
//    private val photosViewModel by lazy {
//        ViewModelProvider(this, photoViewModelFactory).get(PhotosViewModel::class.java)
//    }

    private val photosViewModel by viewModels<PhotosViewModel>{ViewModelFactory()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = this.title

         photosViewModel.getPhotos()
         adapter = PhotosAdapter()
        subscribeToObservers()
        binding.recyclerview.adapter = adapter

       val lm = GridLayoutManager(this, 6)

        lm.spanSizeLookup = object : SpanSizeLookup() {

            override fun getSpanSize(position: Int): Int {
                return when (position%5) {
                     0,1 -> 3
                     else -> 2
                }
//                return when (position) {
//                   0-> 2
//                   else-> 1
//
//           //                    2, 3, 4 -> return 2
//               }
//                throw IllegalStateException("internal error")
            }
        }
        binding.recyclerview.layoutManager = lm


    }
   private fun subscribeToObservers(){

       photosViewModel.obResult.observe(this, {
          when(it){
              is Result.Success->{ adapter.photos = it.value.body()?.toMutableList() }
              is Result.Failure->{Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()}
          }

       })

   }
}