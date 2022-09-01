package com.example.firibasenotifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firibasenotifications.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

const val TOPIC = "/topics/myTopic"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }


        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)



        binding.btnSend.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescrition.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()){
                PushNotification(
                    NotificationData(title, description),
                    TOPIC
                ).also {
                    sendNotification(it)
                }
            }

        }


    }


    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful){
                Log.d("AAAAA", "Response: ${Gson().toJson(response)}")
            } else {
                Log.d("AAAAA", response.errorBody().toString())
            }

        } catch (e: Exception){
            Log.e("AAAAA", e.toString())
        }
    }


}