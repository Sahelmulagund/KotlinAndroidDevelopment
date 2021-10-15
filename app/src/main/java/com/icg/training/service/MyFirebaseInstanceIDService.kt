package com.icg.training.service

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.icg.training.AuthNavigationActivity
import com.icg.training.Constants
import java.util.*

class MyFirebaseInstanceIDService:FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val dataReceived = p0.data

//        Toast.makeText(this, c.name,Toast.LENGTH_SHORT).show()

        if (dataReceived != null){
            val activityName= dataReceived[Constants.Keys.data]

            val intent = Intent(this,AuthNavigationActivity::class.java)
//            val bundle= Bundle()
            intent.putExtra("data", activityName)

            Constants.showNotification(this, Random().nextInt(),
            dataReceived[Constants.NOTI_TITLE],
            dataReceived[Constants.NOTI_CONTENT],
             intent )

        }
    }
}