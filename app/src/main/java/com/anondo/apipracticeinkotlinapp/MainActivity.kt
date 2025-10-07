package com.anondo.apipracticeinkotlinapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var banner_ad_view : AdView = findViewById(R.id.banner_ad_view)
        var progressBar : ProgressBar = findViewById(R.id.progressBar)

        CoroutineScope(Dispatchers.IO).launch {
            MobileAds.initialize(this@MainActivity) {}
        }

        var queue = Volley.newRequestQueue(this)
        var url = "https://arsarkar.xyz/Apps/frist.php"

        val stringRequest = StringRequest(Request.Method.GET, url,  { response ->
            if (response == "ShowAd"){
                progressBar.visibility = View.GONE
                var adRequest = AdRequest.Builder().build()
                banner_ad_view.loadAd(adRequest)
            }else{
                progressBar.visibility = View.GONE
                Toast.makeText(this , "Don't wait", Toast.LENGTH_SHORT).show()
            }
            },
            Response.ErrorListener {
                progressBar.visibility = View.GONE
                Toast.makeText(this , Error().toString(), Toast.LENGTH_SHORT).show()
            })

        queue.add(stringRequest)
    }
}