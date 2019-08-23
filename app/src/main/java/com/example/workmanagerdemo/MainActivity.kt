package com.example.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException

//here we create oneTimeRequest Object....
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("Main Activity" , "[ OnCreate ]")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creating oneTimeRequestObject.....
        val workRequest : OneTimeWorkRequest = OneTimeWorkRequest.Builder(WorkerDemo::class.java).build()

        button.setOnClickListener {
            Log.v("Main Activity", "Button Clicked")
            //getting a Work Manager and enqueuing work request
            WorkManager.getInstance(this).enqueue(workRequest)
        }

        //getting status of work and displaying it on screen
        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(workRequest.id)
            .observe(this, Observer { workInfo : WorkInfo?->
                if(workInfo!=null&&workInfo.state==WorkInfo.State.SUCCEEDED){
                    editText.setText(workInfo.state.name)
                }

            })

    }


}
