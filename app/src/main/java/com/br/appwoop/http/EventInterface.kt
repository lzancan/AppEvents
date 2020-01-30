package com.br.appwoop.http

import com.br.appwoop.objects.CheckinItem
import com.br.appwoop.objects.EventItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface EventInterface {
    @GET("events/{eventId}/")
    fun listEvent(@Path("eventId") event: Int): Call<EventItem>

    @GET("events/")
    fun listAllEvents(): Call<List<EventItem>>

    @POST("checkin/")
    fun postCheckin(@Body checkinItem: CheckinItem): Call<Any>

}