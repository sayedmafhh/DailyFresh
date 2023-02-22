package com.thetrusttech.getacarparts.utils

import android.app.Activity
import android.content.res.AssetManager
import android.view.WindowManager
import androidx.core.view.WindowInsetsControllerCompat
import java.text.SimpleDateFormat
import java.util.*

fun Activity.changeStatusBarColor(color: Int, isLight: Boolean) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = color

    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
}

/*fun Activity.readJsonFromAssets(context: Context, filePath: String): String? {
    try {
        val source = context.assets.open(filePath).bufferedReader().use {
            it.readText()
        }
        return source.readByteString().string(Charset.forName("utf-8"))

    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}*/

fun AssetManager.readAssetsFile(fileName: String): String =
    open(fileName).bufferedReader().use { it.readText() }

fun convertTo12Hours(time: String): String{
    //in => "14:00:00"
    //out => "02:00 PM"
    val inputFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("hh:mm aa", Locale.getDefault())
    val date = inputFormat.parse(time)
    return outputFormat.format(date)
}