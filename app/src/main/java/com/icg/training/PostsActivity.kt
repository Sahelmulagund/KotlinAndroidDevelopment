package com.icg.training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.icg.training.adapter.PhotosAdapter
import com.icg.training.adapter.PostsAdapter
import com.icg.training.databinding.ActivityPostsBinding
import com.icg.training.model.Posts
import com.icg.training.repository.MainRepository
import com.icg.training.retrofit.RetrofitClient
import com.icg.training.viewmodels.PhotosViewModel
import com.icg.training.viewmodels.PostsViewModel

class PostsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPostsBinding
    private val retrofitService = RetrofitClient.getInstance()
    lateinit var adapter: PostsAdapter
    private lateinit var postsViewModelFactor: PostsViewModel.PostViewModelFactory
    private val postsViewModel by lazy {
        ViewModelProvider(this, postsViewModelFactor).get(PostsViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = this.title
        postsViewModelFactor = PostsViewModel.PostViewModelFactory(savedInstanceState,
            MainRepository(retrofitService)
        )
        subscribeToObservers()
        adapter = PostsAdapter(::itemClicked)


       binding.rvPosts.adapter = adapter
        binding.rvPosts.setHasFixedSize(true)
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(this@PostsActivity,RecyclerView.VERTICAL,false )
        }
    }

    private fun itemClicked(posts: Posts) {
        Constants.Num.postId = posts.id
        startActivity((Intent(this,PostDetailActivity::class.java)))
    }

    private fun subscribeToObservers(){
        postsViewModel.getPosts()
        postsViewModel.poststList.observe(this, Observer {
            if (it != null) {
                adapter.posts = it.toMutableList()

            }

        })
        postsViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }
}