package com.icg.training.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icg.training.R
import com.icg.training.model.Comments
import kotlinx.android.synthetic.main.adapter_comment.view.*


class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.VH>() {

    var comments:MutableList<Comments>?=null

        set(value) {
            field = value?.toMutableList()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.adapter_comment, parent, false))

    }


    override fun onBindViewHolder(holder: CommentsAdapter.VH, position: Int) {
        holder.bind(comments?.get(position))

    }

    override fun getItemCount() = comments?.size ?: 0
    inner class VH(itemView : View) : RecyclerView.ViewHolder(itemView){

        init {
            with(itemView){

            }
        }

        fun bind(data: Comments?) {
            //itemView.tag = data
            with(itemView){
                tvName.text = data?.name
                tvEmail.text = data?.email
                tvBody.text = data?.body
            }
        }
    }
}