package com.hooloovoochimico.meno

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.hooloovoochimico.meno.MenoConstant.EXTRA_MODIFY_ID


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val title = findViewById<EditText>(R.id.title)
        val body = findViewById<EditText>(R.id.body)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val note = Notifier(this, 1,Memo(1, title.text.toString(), body.text.toString()))
            note.showNotify()
        }

        if(intent.hasCategory(EXTRA_MODIFY_ID)){
            val testo = "Questa è l'evento di modifica dalla notifica;) ${intent.getIntExtra(EXTRA_MODIFY_ID, 0)}"
            title.setText(testo)
        }





    }
}
