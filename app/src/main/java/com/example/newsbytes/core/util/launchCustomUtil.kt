package com.example.newsbytes.core.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

fun Context.launchUrlInCustomTab(url: String) {
    if (url.isBlank()) {
        Toast.makeText(this, "Invalid link", Toast.LENGTH_SHORT).show()
        return
    }
    try {
        val customTabsIntent =
            CustomTabsIntent.Builder().setShowTitle(true).setInstantAppsEnabled(true).build()

        customTabsIntent.launchUrl(this, url.toUri())
    } catch (e: Exception) {
        Log.e("CustomTab", "Custom Tabs failed, trying standard Intent", e)
    }
}