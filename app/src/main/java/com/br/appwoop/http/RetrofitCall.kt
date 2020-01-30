package com.br.appwoop.http

import android.util.Log
import com.br.appwoop.R
import com.br.appwoop.objects.CheckinItem
import com.br.appwoop.view.adapter.RecyclerAdapter
import com.br.appwoop.view.activity.EventDetailsActivity
import com.br.appwoop.objects.EventItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCall {

    private const val baseUrl = "https://5b840ba5db24a100142dcd8c.mockapi.io/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val service = retrofit.create(EventInterface::class.java)

    fun loadEventList(adapter: RecyclerAdapter) {
        service.listAllEvents().enqueue(object: Callback<List<EventItem>> {
            override fun onFailure(call: Call<List<EventItem>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<EventItem>>, response: Response<List<EventItem>>) {
                if (response.body() != null) {
                    adapter.updateData(ArrayList(response.body()!!))
                }
            }
        })
    }

    fun loadEvent(eventId: Int, eventDetailsActivity: EventDetailsActivity) {
        service.listEvent(eventId).enqueue(object: Callback<EventItem> {
            override fun onFailure(call: Call<EventItem>, t: Throwable) {
            }

            override fun onResponse(call: Call<EventItem>, response: Response<EventItem>) {
                if (response.body() != null) {
                    eventDetailsActivity.updateInterface(response.body()!!)
                }
            }
        })
    }

    fun postCheckin(checkinItem: CheckinItem, eventDetailsActivity: EventDetailsActivity) {
        service.postCheckin(checkinItem).enqueue(object: Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                eventDetailsActivity.showCheckinResult(eventDetailsActivity.getString(R.string.checkin_fail))
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if(response.body().toString().contains("code=200")) {
                    eventDetailsActivity.showCheckinResult(eventDetailsActivity.getString(R.string.checkin_successful))
                } else{
                    eventDetailsActivity.showCheckinResult(eventDetailsActivity.getString(R.string.checkin_fail) + " - ${response.code()}")
                }
            }
        })
    }


}