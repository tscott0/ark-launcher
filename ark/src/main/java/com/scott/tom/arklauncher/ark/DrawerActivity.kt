package com.scott.tom.arklauncher.ark

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class DrawerActivity : AppCompatActivity() {

    private val appDrawerColumns = 5

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_drawer)

        val context = this.applicationContext
        val pm = context?.packageManager

        viewManager = GridLayoutManager(context, appDrawerColumns)

        if (pm != null) {
            val packageInfo = pm.getInstalledPackages(PackageManager.PERMISSION_GRANTED)
            val packages = packageInfo.filter {
                pm.getLaunchIntentForPackage(it.packageName) != null
            }.filter {
                pm.getApplicationIcon(it.packageName) != null
            }.mapNotNull {
                //                val drawable = getActivityIcon(pm, it.packageName, "com.google.android.apps.chrome.Main")
                val drawable = pm.getApplicationIcon(it.packageName)
                val onClick = {
                    val launchIntent = pm.getLaunchIntentForPackage(it.packageName)
                    if (launchIntent != null) startActivity(launchIntent)
                }
                val appLabel = pm.getApplicationLabel(it.applicationInfo).toString()

                Triple(appLabel, drawable, onClick)
            }

            viewAdapter = PageIconAdapter(packages)
        }

        recyclerView = findViewById<RecyclerView>(R.id.drawer_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

    }


}