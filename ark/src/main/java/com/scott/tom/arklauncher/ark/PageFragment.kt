package com.scott.tom.arklauncher.ark

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.view.*

class PageFragment : Fragment() {

    private val appDrawerColumns = 5

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))

        val context = rootView.context
        val pm = context?.packageManager

        viewManager = GridLayoutManager(context, appDrawerColumns)

        if (pm != null) {
            val packageInfo = pm.getInstalledPackages(PackageManager.PERMISSION_GRANTED)
            val packages = packageInfo.filter {
                pm.getLaunchIntentForPackage(it.packageName) != null
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

        recyclerView = rootView.findViewById<RecyclerView>(R.id.page_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        return rootView
    }

    companion object {
        private val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}