package com.br.appwoop.http

import com.br.appwoop.objects.EventItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface EventInterface {
    @GET("{eventId}/")
    fun listEvent(@Path("eventId") event: Int): Call<EventItem>

    @GET(".")
    fun listAllEvents(): Call<List<EventItem>>

}