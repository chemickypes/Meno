package com.hooloovoochimico.meno

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.Button
import com.crashlytics.android.Crashlytics



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val note = Notifier(this, 1,Memo(1, "prova1", "gne gne gne"))
            note.showNotify()
        }



    }
}
