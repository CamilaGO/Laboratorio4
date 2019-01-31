package com.example.camila.lab4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class InicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
    }
    //Boton para regresar a la MainActivity en donde esta el navigation drawer
    fun onClick_Back(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
