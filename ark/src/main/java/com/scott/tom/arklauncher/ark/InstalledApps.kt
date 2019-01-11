package com.scott.tom.arklauncher.ark

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.AsyncTask
import android.provider.Settings

object InstalledApps: AsyncTask<Context, Int, List<AppDetails>>() {

    private var appList: List<AppDetails> = listOf()

    var startTime: Long = 0
    var endTime: Long = 0

    var appCount = 0

    override fun doInBackground(vararg params: Context): List<AppDetails> {
        startTime = System.nanoTime()

        if (appList.isNotEmpty()) {
            return appList
        }

        val context = params[0]
        val pm = context.packageManager

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

                appCount++

                AppDetails(appLabel, drawable, onClick, onLongClick)
            }
        }

        return appList
    }

    override fun onPostExecute(result: List<AppDetails>?) {
        super.onPostExecute(result)

        endTime = System.nanoTime()
//        val seconds = endTime.minus(startTime) / 1_000_000_000.0
    }
}

