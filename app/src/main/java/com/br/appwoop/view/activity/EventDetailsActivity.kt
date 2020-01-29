package com.br.appwoop.view.activity

import android.app.Activity
import android.os.Bundle
import com.br.appwoop.R
import com.br.appwoop.http.RetrofitCall
import com.br.appwoop.objects.EventItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*

class EventDetailsActivity: Activity() {

    companion object{
        const val EVENT_ITEM_ID = "EVENT_ITEM_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val eventId = intent.extras?.get(EVENT_ITEM_ID) as Int

        RetrofitCall.loadEvent(eventId, this@EventDetailsActivity)
    }

    fun updateInterface(eventItem: EventItem){
        tvEventName.text = eventItem.title
        Glide.with(applicationContext).load(eventItem.image).into(ivEventImage)
    }
}