package com.br.appwoop.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.br.appwoop.R
import com.br.appwoop.http.RetrofitCall
import com.br.appwoop.objects.CheckinItem
import com.br.appwoop.objects.EventItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.dialog_checkin.view.*

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

        tvTextCheckin.setOnClickListener{
            showDialog(eventItem)
            RetrofitCall.postCheckin(CheckinItem(1, "LZ", "luciano@gmail.com"), this@EventDetailsActivity)
        }

        tvTextShare.setOnClickListener{
            shareEvent(eventItem)
        }

    }

    private fun shareEvent(eventItem: EventItem){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,
                getString(R.string.look_event) +" " + eventItem.title + " \n " + eventItem.description
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun showDialog(eventItem: EventItem){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_checkin, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle(getString(R.string.checkin))
        val  mAlertDialog = builder.show()

        dialogView.btnCheckin.setOnClickListener {
            mAlertDialog.dismiss()
            val name = dialogView.etDialogName.text.toString()
            val email = dialogView.etDialogEmail.text.toString()
            RetrofitCall.postCheckin(CheckinItem(eventItem.id, name, email), this@EventDetailsActivity)
        }
    }

    fun showCheckinResult(result: String){
        Toast.makeText(this@EventDetailsActivity, result, Toast.LENGTH_SHORT).show()
    }
}