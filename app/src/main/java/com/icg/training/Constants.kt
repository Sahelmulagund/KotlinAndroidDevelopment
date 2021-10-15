package com.icg.training

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

object Constants {
    const val NOTI_TITLE = "title"
    const val NOTI_CONTENT = "content"
    fun showNotification(context: Context, id: Int, title: String?, content: String?, intent: Intent?) {
        var pendingIntent:PendingIntent?=null
        if (intent != null){

            pendingIntent = PendingIntent.getActivity(context,id, intent,PendingIntent.FLAG_UPDATE_CURRENT)
        }


        val NOTIFICATION_CHANNEL_ID = "com.icg.training"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID,
                "Training", NotificationManager.IMPORTANCE_DEFAULT)

            notificationChannel.description = "Training"
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.lightColor = (Color.CYAN)
            notificationChannel.vibrationPattern = longArrayOf(0,1000,500,1000)

            notificationManager.createNotificationChannel(notificationChannel)
        }
        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        builder.setContentTitle(title!!).setContentText(content!!).setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_notifications_black_24dp))
        if (pendingIntent != null)
            builder.setContentIntent(pendingIntent)

        val notification = builder.build()

        notificationManager.notify(id,notification)
    }

    object Codes {
        const val REQ_CODE = 1001
    }

    object Keys{
        const val lat = "lat"
        const val lng = "lng"
        const val data = "data"
    }
    object Num{
        const val NUM_PAGE=5
        var postId:Int?=null
    }
    object ROUTES{


        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val PHOTO_URL = "photos"
        const val POSTS_URL = "posts"
        const val COMMENT_URL = "comments"

    }
}