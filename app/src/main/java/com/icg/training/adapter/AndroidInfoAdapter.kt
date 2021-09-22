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
import kotlinx.android.synthetic.main.layout_recycler_android_versions.view.*

class AndroidInfoAdapter(val callback:(InfoModel?,Int) -> Unit, val deleteCallback:(Int)->Unit):RecyclerView.Adapter<AndroidInfoAdapter.VH>() {


  var versionListItems:ArrayList<InfoModel>?=null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class VH(itemView : View) : RecyclerView.ViewHolder(itemView){

        init {
            with(itemView){
                itemView.setOnClickListener {
                    //callback.invoke(itemView.tag as String)
                    callback.invoke(versionListItems?.get(adapterPosition),adapterPosition)

                }
                deleteImg.setOnClickListener {
                    deleteCallback.invoke(adapterPosition)

                }
            }
        }

        fun bind(data: InfoModel?) {
            //itemView.tag = data
            with(itemView){
                androidNameLetter.text = data!!.androidName?.get(0).toString()!!
                name.text = data?.androidName
                version.text = data?.androidVersion
                sdk.text = data?.androidSdk.toString()
                desc.text = data?.androidDesc.toString()
                releasedDate.text = data?.releaseDate




            }
        }
    }
    fun updateItem(position: Int,infoModel: InfoModel){
        versionListItems?.set(position,infoModel)
    }
    fun insertItem(position: Int,infoModel: InfoModel){
        versionListItems?.add(position,infoModel)
    }
    fun deleteItem(position: Int){
        versionListItems?.removeAt(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_android_versions, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(versionListItems?.get(position))

    }

    override fun getItemCount() = versionListItems?.size ?: 0
}