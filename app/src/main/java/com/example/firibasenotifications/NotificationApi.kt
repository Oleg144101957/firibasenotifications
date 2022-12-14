package com.example.firibasenotifications

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {


    @Headers("Authorization: key=${Const.SERVER_KEY}", "Content-Type:${Const.CONTENT_TYPE}")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ) : Response<ResponseBody>

}