package com.example.firibasenotifications

data class PushNotification(
    val data: NotificationData,
    val to: String
)