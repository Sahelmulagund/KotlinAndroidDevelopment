package com.icg.training.listeners

import android.view.View

interface IRecyclerItemClickListener {
    fun onItemClick(view: View, pos:Int)
}