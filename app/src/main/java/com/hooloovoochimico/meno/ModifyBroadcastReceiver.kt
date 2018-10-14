package com.hooloovoochimico.meno

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hooloovoochimico.meno.MenoConstant.EXTRA_MODIFY_ID

class ModifyBroadcastReceiver: BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val mainIntent = Intent(context, MainActivity::class.java)
        val id = intent?.getIntExtra(EXTRA_MODIFY_ID, 0)
        mainIntent.putExtra(EXTRA_MODIFY_ID, id)
        context?.startActivity(mainIntent)
    }

}