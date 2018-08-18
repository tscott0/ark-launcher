package com.scott.tom.arklauncher.ark

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class DrawerActivity : AppCompatActivity() {

    private val appDrawerColumns = 6

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_drawer)

        val context = this.applicationContext
        viewManager = GridLayoutManager(context, appDrawerColumns)

        val appList = InstalledApps.get()

        viewAdapter = PageIconAdapter(appList)

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