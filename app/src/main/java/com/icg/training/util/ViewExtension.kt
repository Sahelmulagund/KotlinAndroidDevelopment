package com.icg.training.util

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.*

fun Context.showToast(msg:String){
    Toast.makeText(this, msg,Toast.LENGTH_SHORT).show()
}

fun CoroutineScope.launchPeriodically(repeatInMillis:Long,action: ()->Unit)
 = this.launch {
    withContext(Dispatchers.Main){
        if (repeatInMillis > 0){
            while (isActive){
                action()
                delay(repeatInMillis)
            }
        }else{
            action()
        }
    }

}