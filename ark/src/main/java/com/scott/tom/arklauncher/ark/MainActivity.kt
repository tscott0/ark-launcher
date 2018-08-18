package com.scott.tom.arklauncher.ark

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container = findViewById<RelativeLayout>(R.id.page_layout)
        val debugTextView = findViewById<TextView>(R.id.debug)

        InstalledApps.execute(this.applicationContext)

        InstalledApps.onAppAdded = { appCount: Int ->
            debugTextView.text = "${appCount.toString()} apps"
        }

        InstalledApps.onPostExecuteCallback = { appCount: Int, duration: Double ->
            debugTextView.text = "${appCount} apps in ${duration}s"
        }

        val appDrawerButton = container.findViewById<ImageView>(R.id.show_drawer_button)
        appDrawerButton.setOnClickListener {
            val intent = Intent(baseContext, DrawerActivity::class.java)
            startActivity(intent)
        }
    }
}
