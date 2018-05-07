package com.scott.tom.arklauncher.ark

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_main.view.*

class PageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))

        val appDrawerButton = rootView.findViewById<ImageView>(R.id.show_drawer_button)
        appDrawerButton.setOnClickListener {
            val intent = Intent(this.context, DrawerActivity::class.java)
            startActivity(intent)
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