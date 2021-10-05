package com.icg.training.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.icg.training.R
import com.icg.training.model.Photos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_photo.view.*


class PhotosAdapter():RecyclerView.Adapter<PhotosAdapter.VH>() {
    private val LAYOUT_ONE = 0
    private val LAYOUT_TWO = 1
    var photos:MutableList<Photos>?=null

    set(value) {
      field = value?.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosAdapter.VH {
       return VH(LayoutInflater.from(parent.context).inflate(R.layout.adapter_photo, parent, false))

    }

    override fun getItemViewType(position: Int): Int {
        if (position%3==0){

            return LAYOUT_ONE
        }else{


            return LAYOUT_TWO
        }
    }
    override fun onBindViewHolder(holder: PhotosAdapter.VH, position: Int) {
        holder.bind(photos?.get(position))

    }

    override fun getItemCount() = photos?.size ?: 0
    inner class VH(itemView : View) : RecyclerView.ViewHolder(itemView){

        init {
            with(itemView){

            }
        }

        fun bind(data: Photos?) {
            //itemView.tag = data
            with(itemView){

                    Picasso.get().load(data?.thumbnailUrl).into(imageview)



            }
        }
    }
}