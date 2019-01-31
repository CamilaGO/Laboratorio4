package com.example.camila.lab4

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web2.*

class Web2Activity : AppCompatActivity() {

    //Se guarda el link deseado en una variable
    private val URL2 = "https://github.com/CamilaGO/App_Contactos"
    private var isCreated2 = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web2)
        //Se inicia la imagen animada mientras la pagina carga
        startLoaderAnimate()

        webview2.settings.javaScriptEnabled = true
        webview2.settings.setSupportZoom(false)
        //Para acceder a los links de la web se inicia un webViewClient
        webview2.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                endLoaderAnimate()
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                endLoaderAnimate()
                showErrorDialog("Error",
                    "No internet connection.  Please check your connection",
                    this@Web2Activity)
            }
        }
        webview2.loadUrl(URL2)  //Se carga la URL
    }

    override fun onResume() {
        super.onResume()

        if (isCreated2 && !isNetworkAvailable()){
            isCreated2 = false
            showErrorDialog("Error", "No internet connection.  Please check your connection",
                this@Web2Activity)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        //Se evalua la conexion
        val connectionManager =
            this@Web2Activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview2.canGoBack()) {
            webview2.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    //funcion utilizada para mostrar mensaje de error de ser necesario
    private fun showErrorDialog(title: String, message: String, context: Context){
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.setNegativeButton("Cancel", { _, _ ->
            this.finish()
        })
        dialog.setNeutralButton("Settings", {_, _ ->
            startActivity(Intent(Settings.ACTION_SETTINGS))
        })
        dialog.setPositiveButton("Retry", { _, _ ->
            this.recreate()
        })
        dialog.create().show()
    }

    //funciones para la imagen animada mientras el link carga
    private fun endLoaderAnimate(){
        loaderImage2.clearAnimation()
        loaderImage2.visibility = View.GONE
    }

    private fun startLoaderAnimate(){
        val objectAnimator = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                val startHeight = 170
                val newHeight = (startHeight * (startHeight+40) * interpolatedTime).toInt()
                loaderImage2.layoutParams.height = newHeight
                loaderImage2.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        objectAnimator.repeatCount = -1
        objectAnimator.repeatMode = ValueAnimator.REVERSE
        objectAnimator.duration = 1000
        loaderImage2.startAnimation(objectAnimator)
    }
}
