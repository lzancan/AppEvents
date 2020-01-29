package com.br.appwoop.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.appwoop.R
import com.br.appwoop.view.activity.EventDetailsActivity
import com.br.appwoop.objects.EventItem
import com.bumptech.glide.Glide

class RecyclerAdapter(private val context: Context, private val eventList: ArrayList<EventItem>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //get view reference
        var textTitle: TextView = view.findViewById(R.id.eventTitle) as TextView
        val itemBackGround: ImageView = view.findViewById(R.id.itemBackground) as ImageView
        val backGroundLayout: FrameLayout = view.findViewById(R.id.backgroundLayout) as FrameLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_event_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text =  eventList[position].title
        Glide.with(context).load(eventList[position].image).into(holder.itemBackGround)
        holder.backGroundLayout.setOnClickListener{
            val intent = Intent(context, EventDetailsActivity::class.java)
            intent.putExtra(EventDetailsActivity.EVENT_ITEM_ID, eventList[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    fun updateData(listOfEvents: ArrayList<EventItem>) {
        eventList.clear()
        notifyDataSetChanged()
        eventList.addAll(listOfEvents)
        notifyDataSetChanged()

    }
}