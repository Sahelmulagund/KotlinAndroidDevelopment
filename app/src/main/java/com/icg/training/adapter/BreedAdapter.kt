package com.icg.training.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icg.training.R
import com.icg.training.model.InfoModel
import com.icg.training.presentation.dogsbreed.model.DogsBreedImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_recycler_android_versions.view.*
import kotlinx.android.synthetic.main.rv_breed_imaged.view.*

class BreedAdapter(val callable:(String)->Unit):RecyclerView.Adapter<BreedAdapter.VH>() {
    var breed:List<String>?=null
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var imgDog:List<DogsBreedImage>?=null
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

        fun bind(data: String?) {
            //itemView.tag = data
            with(itemView){
                tvBreedName.text = data
                if (imgDog?.isNotEmpty() == true){
                    Picasso.get().load(imgDog?.get(adapterPosition)?.message).into(ivBreed)
                }
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