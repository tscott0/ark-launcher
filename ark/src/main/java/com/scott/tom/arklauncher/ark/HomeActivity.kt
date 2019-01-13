package com.scott.tom.arklauncher.ark

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    lateinit var debugTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val container = findViewById<RelativeLayout>(R.id.page_layout)
        val debugTextView = findViewById<TextView>(R.id.debug)

        if (InstalledApps.status == AsyncTask.Status.PENDING) {
            InstalledApps.execute(this.applicationContext)
        }


        val appDrawerButton = container.findViewById<ImageView>(R.id.show_drawer_button)
        appDrawerButton.setOnClickListener {
            val intent = Intent(baseContext, DrawerActivity::class.java)
            startActivity(intent)
        }
    }
}
