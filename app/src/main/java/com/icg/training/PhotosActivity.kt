package com.icg.training

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.icg.training.adapter.PhotosAdapter
import com.icg.training.databinding.ActivityPhotosBinding
import com.icg.training.repository.MainRepository
import com.icg.training.retrofit.RetrofitClient
import com.icg.training.viewmodels.PhotosViewModel
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.common_app_bar.*

class PhotosActivity : AppCompatActivity() {
   private lateinit var binding:ActivityPhotosBinding
    private val retrofitService = RetrofitClient.getInstance()
    lateinit var adapter:PhotosAdapter
    private lateinit var photoViewModelFactory:PhotosViewModel.PhotoViewModelFactory
    private val photosViewModel by lazy {
        ViewModelProvider(this, photoViewModelFactory).get(PhotosViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = this.title

         photoViewModelFactory = PhotosViewModel.PhotoViewModelFactory(savedInstanceState,
             MainRepository(retrofitService)
         )

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
       photosViewModel.getPhotos()
       photosViewModel.photoList.observe(this, Observer {
           if (it != null) {
               adapter.photos = it.toMutableList()

           }

       })
       photosViewModel.errorMessage.observe(this, Observer {
           Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
       })

   }
}