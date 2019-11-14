package com.example.voice_recognition

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnFalar: Button
    private lateinit var txtTextoRecebido: TextView

    private var ID_TEXTO_VOZ = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFalar = findViewById(R.id.btnFalar)
        txtTextoRecebido = findViewById(R.id.txtFalar)

        btnFalar.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale algo normalmente")

            try {
                startActivityForResult(intent, ID_TEXTO_VOZ)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Este dispositivo não suporta comando de voz...", Toast.LENGTH_SHORT).show()
            }

        }

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
