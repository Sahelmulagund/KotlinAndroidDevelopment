package com.icg.training

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.icg.training.adapter.PostsAdapter
import com.icg.training.databinding.ActivityPostsBinding
import com.icg.training.model.Posts
import com.icg.training.result.Result
import com.icg.training.viewmodelfactory.ViewModelFactory
import com.icg.training.viewmodels.PostsViewModel

class PostsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPostsBinding
//    private val retrofitService = RetrofitClient.getInstance()
    lateinit var adapter: PostsAdapter
//    private  var postsViewModelFactor: PostsViewModel.PostViewModelFactory
//    private val postsViewModel by lazy {
//        ViewModelProvider(this, postsViewModelFactor).get(PostsViewModel::class.java)
//    }

    private val postsViewModel by viewModels<PostsViewModel> { ViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = this.title
//        postsViewModelFactor = PostsViewModel.PostViewModelFactory(savedInstanceState,
//            MainRepository(retrofitService)
//        )
        postsViewModel.getPosts()
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

    private fun subscribeToObservers() {
        postsViewModel.postsList.observe(this, {
            when(it){
                is Result.Success->{ adapter.posts = it.value.body()?.toMutableList() }
                is Result.Failure->{Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()}
            }
        })
    }
}
