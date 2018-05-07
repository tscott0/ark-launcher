package com.scott.tom.arklauncher.ark

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class PageIconAdapter(private val myDataset: List<Triple<String, Drawable, () -> Unit>>) :
        RecyclerView.Adapter<PageIconAdapter.IconViewHolder>() {

    // A custom ViewHolder that contains a reference to both the icon and the title
    inner class IconViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var icon: ImageView = view.findViewById(R.id.icon_app_button) as ImageView
        var title: TextView = view.findViewById<View>(R.id.icon_app_name) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PageIconAdapter.IconViewHolder {
        val linearLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_icon, parent, false) as LinearLayout

        return IconViewHolder(linearLayout)
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        val (title, drawable, onClick) = myDataset[position]

        holder.icon.setImageDrawable(drawable)
        holder.icon.setOnClickListener { _ -> onClick() }

        holder.title.text = title
    }

    override fun getItemCount() = myDataset.size

}