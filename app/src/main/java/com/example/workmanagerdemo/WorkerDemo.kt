package com.example.workmanagerdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

const val Channel_ID = "random_ID"
const val Channel_Name = "random_Name"
const val Channel_Importance = NotificationManager.IMPORTANCE_DEFAULT //noise but not visible

//our worker class displays a notification

class WorkerDemo (private val context: Context , workerParameters: WorkerParameters): Worker(context , workerParameters) {


    //inside this method we will implement the work which we need to perform in the background
    override fun doWork(): Result {
        Log.v("WokerDemo" , "[ doWork ]")
        //we are displaying some notification
        displayNotification("Your Mobile phone is being Hacked" , "Installing required viruses and breaking your codes")
        return Result.Success()
    }

    private fun displayNotification (task : String, desc : String) {

        for(i in 1 ..1000000){
            Log.v("Worker Demo" , "hello there")
        }

        //task is the title of notification and desc is text inside notification

        //for android os version above oreo ...we need to create notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Log.v("WorkerDemo" , "[ displayNotification ]")
            //creating notification manager
            val notificationManager :NotificationManager= context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            //declaring notification channel
            val notificationChannel = NotificationChannel(Channel_ID , Channel_Name , Channel_Importance )

            //creating notification channel
            notificationManager.createNotificationChannel(notificationChannel)

            //building a notification
            val notificationBuilder = NotificationCompat.Builder(applicationContext , Channel_ID )
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher)

            //displaying the notification
            notificationManager.notify(1,notificationBuilder.build() )

        }
    }

}