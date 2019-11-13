package com.example.voice_recognition

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var btnFalar: Button
    private lateinit var txtTextoRecebido: TextView

    private var ID_TEXTO_VOZ = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFalar = findViewById(R.id.btnFalar)
        txtTextoRecebido = findViewById(R.id.txtFalar)

        //btnFalar.setOnClickListener(clickListenter)

        //override fun onClick()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            ID_TEXTO_VOZ ->
                    if (resultCode == Activity.RESULT_OK && data != null) {
                        var resultado = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                        var ditado = resultado.get(0)

                        Toast.makeText(applicationContext, ditado, Toast.LENGTH_LONG).show()

                        txtTextoRecebido.setText(ditado)
                    }
        }
    }
}
