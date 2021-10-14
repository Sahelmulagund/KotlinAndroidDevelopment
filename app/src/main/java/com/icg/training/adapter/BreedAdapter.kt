package com.icg.training.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.icg.training.R
import com.icg.training.presentation.dogsbreed.model.Dog
import com.icg.training.util.GlideApp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_breed_imaged.view.*

class BreedAdapter:RecyclerView.Adapter<BreedAdapter.VH>() {
    var breed:List<Dog>?=null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class VH(itemView : View) : RecyclerView.ViewHolder(itemView){

        init {
            with(itemView){
//                breed?.get(adapterPosition)?.let { callable.invoke(it) }


            }
        }

        fun bind(data: Dog?) {
            //itemView.tag = data
            with(itemView){
                tvBreedName.text = data?.breed
//                GlideApp.with(context).asBitmap().load(data?.imageUrl).apply( RequestOptions().diskCacheStrategy(
//                    DiskCacheStrategy.NONE)).into(ivBreed)
                Picasso.get().load(data?.imageUrl).into(ivBreed)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.rv_breed_imaged, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
       holder.bind(breed?.get(position))

    }

    override fun getItemCount() = breed?.size ?: 0

}