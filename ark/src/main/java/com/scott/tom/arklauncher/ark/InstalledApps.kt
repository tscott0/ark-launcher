package com.scott.tom.arklauncher.ark

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.support.v7.widget.RecyclerView

object InstalledApps {

    private lateinit var appList: List<AppDetails>
    private lateinit var viewManager: RecyclerView.LayoutManager

    fun initFromContext(context: Context) {
        val pm = context?.packageManager

        if (pm != null) {
            val packageInfo = pm.getInstalledPackages(PackageManager.PERMISSION_GRANTED)
            appList = packageInfo.filter {
                pm.getLaunchIntentForPackage(it.packageName) != null
            }.filter {
                pm.getApplicationIcon(it.packageName) != null
            }.mapNotNull {
                val onClick = {
                    val launchIntent = pm.getLaunchIntentForPackage(it.packageName)
                    if (launchIntent != null) context.startActivity(launchIntent)
                }
                val onLongClick = {
                    // TODO: Will this always work?
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.parse("package:${it.packageName}")
                    if (uri != null) {
                        intent.data = uri
                        context.startActivity(intent)
                    }
                    true
                }
                val appLabel = pm.getApplicationLabel(it.applicationInfo).toString()
                val drawable = pm.getApplicationIcon(it.packageName)

                AppDetails(appLabel, drawable, onClick, onLongClick)
            }
        }
    }

    fun list(context: Context): List<AppDetails> {
        if (appList == null) {
            initFromContext(context)
        }
        return appList
    }


    // Return List<AppDetails>

}