package com.icg.training

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.icg.training.adapter.CommentsAdapter
import com.icg.training.databinding.ActivityPostDetailBinding
import com.icg.training.result.Result
import com.icg.training.viewmodelfactory.ViewModelFactory
import com.icg.training.viewmodels.PostsViewModel

class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPostDetailBinding

    lateinit var adapter: CommentsAdapter
//    private  var postsViewModelFactor: PostsViewModel.PostViewModelFactory
    private val postsViewModel by viewModels<PostsViewModel> { ViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = this.title


        adapter = CommentsAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@PostDetailActivity,RecyclerView.VERTICAL,false)
            addItemDecoration(DividerItemDecoration(this@PostDetailActivity, RecyclerView.VERTICAL))

        }


        Constants.Num.postId?.let { postsViewModel.getPostDetails(it) }
        Constants.Num.postId?.let { postsViewModel.getCommentList(it) }
        subscribeToObservers()




    }

    private fun subscribeToObservers(){




        postsViewModel.postDetail.observe(this, {
            when(it){
                is Result.Success->{ binding.tvTitle.text = it.value.body()?.title
                                     binding.tvBody.text = it.value.body()?.body
                }
                is Result.Failure->{Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()}
            }

        })

        postsViewModel.commentList.observe(this,  {
            when(it){
                is Result.Success->{
                    adapter.comments = it.value.body()?.toMutableList()
                }
                is Result.Failure->{Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()}
            }
        })

    }
}
