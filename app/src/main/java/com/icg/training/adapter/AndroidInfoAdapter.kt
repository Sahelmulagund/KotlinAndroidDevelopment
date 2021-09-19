package com.icg.training.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.icg.training.R
import com.icg.training.listeners.IRecyclerItemClickListener
import com.icg.training.model.InfoModel

class AndroidInfoAdapter(internal var context: Context,  internal var infoModelList : MutableList<InfoModel>):RecyclerView.Adapter<AndroidInfoAdapter.MyViewHolder>() {




    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var androidFirstLetter:TextView?=null
        var androidName:TextView?=null
        var androidVersion:TextView?=null
        var androidSdk:TextView?=null
        var androidReleasedDate:TextView?=null
        var androidDesc:TextView?=null

        internal var listener: IRecyclerItemClickListener? = null

        fun setListner(listener: IRecyclerItemClickListener) {
            this.listener = listener
        }

        init {
            androidFirstLetter = itemView.findViewById(R.id.androidNameLetter)
            androidName = itemView.findViewById(R.id.name)
            androidVersion = itemView.findViewById(R.id.version)
            androidSdk = itemView.findViewById(R.id.sdk)
            androidReleasedDate = itemView.findViewById(R.id.releasedDate)
            androidDesc = itemView.findViewById(R.id.desc)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener!!.onItemClick(v!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_recycler_android_versions, parent,false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.androidFirstLetter!!.setText(infoModelList.get(position).androidFirstLetter)
        holder.androidName!!.setText(infoModelList.get(position).androidName)
        holder.androidDesc!!.setText(infoModelList.get(position).androidDesc)
        holder.androidReleasedDate!!.setText(infoModelList.get(position).releaseDate)
        ("Version "+infoModelList.get(position).androidVersion.toString()).also { holder.androidVersion!!.text = it }
        ("SDK "+infoModelList.get(position).androidSdk.toString()).also { holder.androidSdk!!.text = it }

        holder.setListner(object : IRecyclerItemClickListener {
            override fun onItemClick(view: View, pos: Int) {
              Snackbar.make(holder.androidName!!, ""+infoModelList.get(pos).androidName, Snackbar.LENGTH_SHORT).show()
            }

        })

    }

    override fun getItemCount(): Int {
        return infoModelList.size
    }
}