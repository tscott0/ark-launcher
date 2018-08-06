package com.scott.tom.arklauncher.ark

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container = findViewById<RelativeLayout>(R.id.page_layout)

        InstalledApps.initFromContext(this.applicationContext)

        val appDrawerButton = container.findViewById<ImageView>(R.id.show_drawer_button)
        appDrawerButton.setOnClickListener {
            val intent = Intent(baseContext, DrawerActivity::class.java)
            startActivity(intent)
        }
    }
}
