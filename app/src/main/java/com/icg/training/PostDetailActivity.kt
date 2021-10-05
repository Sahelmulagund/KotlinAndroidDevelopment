package com.icg.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.icg.training.adapter.CommentsAdapter
import com.icg.training.adapter.PostsAdapter
import com.icg.training.databinding.ActivityPostDetailBinding
import com.icg.training.repository.MainRepository
import com.icg.training.retrofit.RetrofitClient
import com.icg.training.viewmodels.PostsViewModel

class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPostDetailBinding
    private val retrofitService = RetrofitClient.getInstance()
    lateinit var adapter: CommentsAdapter
    private lateinit var postsViewModelFactor: PostsViewModel.PostViewModelFactory
    private val postsViewModel by lazy {
        ViewModelProvider(this, postsViewModelFactor).get(PostsViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = this.title

        postsViewModelFactor = PostsViewModel.PostViewModelFactory(savedInstanceState,
            MainRepository(retrofitService)
        )
        adapter = CommentsAdapter()
        subscribeToObservers()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@PostDetailActivity,RecyclerView.VERTICAL,false)
            addItemDecoration(DividerItemDecoration(this@PostDetailActivity, RecyclerView.VERTICAL))

        }

    }

    private fun subscribeToObservers(){
        Constants.Num.postId?.let { postsViewModel.getPostDetails(it) }

        Constants.Num.postId?.let { postsViewModel.getCommentList(it) }

        postsViewModel.postDetail.observe(this, Observer {
            if (it != null) {
                binding.tvTitle.text = it.title
                binding.tvBody.text = it.body

            }

        })
        postsViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        postsViewModel.commentList.observe(this, Observer {
            if (it != null){
                adapter.comments = it.toMutableList()
            }
        })

    }
}