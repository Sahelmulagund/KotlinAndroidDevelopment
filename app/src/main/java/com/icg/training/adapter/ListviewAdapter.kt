package com.icg.training.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.icg.training.R
import com.icg.training.model.InfoModel

class ListviewAdapter(internal var context: Context, internal var infoModelList : MutableList<InfoModel>):ArrayAdapter<InfoModel>(context,0, infoModelList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_android_versions, parent, false)

        }


      var  androidFirstLetter = view!!.findViewById(R.id.androidNameLetter) as TextView
        var androidName = view!!.findViewById(R.id.name)as TextView
        var  androidVersion = view!!.findViewById(R.id.version) as TextView
        var  androidSdk = view!!.findViewById(R.id.sdk)as TextView
        var  androidReleasedDate = view!!.findViewById(R.id.releasedDate)as TextView
        var androidDesc = view!!.findViewById(R.id.desc)as TextView

        androidFirstLetter!!.setText(infoModelList.get(position).androidName?.get(0).toString())
       androidName!!.setText(infoModelList.get(position).androidName)
        androidDesc!!.setText(infoModelList.get(position).androidDesc)
       androidReleasedDate!!.setText(infoModelList.get(position).releaseDate)
        ("Version "+infoModelList.get(position).androidVersion.toString()).also { androidVersion!!.text = it }
        ("SDK "+infoModelList.get(position).androidSdk.toString()).also { androidSdk!!.text = it }


        return view!!
    }
}