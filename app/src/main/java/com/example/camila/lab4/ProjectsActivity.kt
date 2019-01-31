package com.example.camila.lab4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.util.*
import kotlin.concurrent.schedule

class ProjectsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

        Timer().schedule(3000){
            val intent = Intent(this@ProjectsActivity, WebActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //Boton para ir a la WebActivity en donde esta el webView de github con el lab 2
    fun onClick_Lab2(view: View){
        val intent = Intent(this@ProjectsActivity, WebActivity::class.java)
        startActivity(intent)
    }

    //Boton para ir a la Web2Activity en donde esta el webView de github con el lab 3
    fun onClick_Lab3(view: View){
        val intent = Intent(this@ProjectsActivity, Web2Activity::class.java)
        startActivity(intent)
    }
}
