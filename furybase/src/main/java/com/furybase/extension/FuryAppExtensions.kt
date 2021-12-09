package com.furybase.extension

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import java.io.File

/***
 * Created By Amir Fury on December 9 2021
 *
 * Email: Fury.amir93@gmail.com
 * */

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q, lambda = 0)
inline fun <T> sdk29OrUp(onSdk29: () -> T): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        onSdk29()
    } else null
}


fun Int.dpToPx(): Int {
    return this.toFloat().dpToPx()
}

fun Float.dpToPx(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    ).toInt()
}

fun Context.string(strResId: Int): String = resources.getString(strResId)

fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(): T {
    return ViewModelProvider(this).get(T::class.java)
}

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(): T {
    return ViewModelProvider(this).get(T::class.java)
}

inline fun <reified T> Gson.fromJson(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)

inline fun <reified T : Any> T.json(): String = Gson().toJson(this, T::class.java)

fun Any.printLog(tag: String, message: String?) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, message.toString())
    }
}

fun Context.openEmailApp(emailAddress: String) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:$emailAddress")

    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "Email App Is Not Available", Toast.LENGTH_SHORT).show()
    }
}

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return (activeNetwork != null && activeNetwork.isConnected)
}

@SuppressLint("HardwareIds")
fun Context.findDeviceId(): String =
    Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

fun FragmentActivity.addFragment(containerId: Int, fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(containerId, fragment, fragment.tag)
    transaction.commit()
}

fun FragmentActivity.addFragmentToBackStack(containerId: Int, fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(containerId, fragment, fragment.tag)
    transaction.addToBackStack(fragment.tag)
    transaction.commit()
}


fun Context.fileSizeInMB(videoPath: String?): Long {
    val file = File(videoPath ?: "")
    return file.length() / (1024 * 1024)
}

fun Any.isGreaterThanOrEqualToOreo(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun Any.isGreaterThanOrEqualToMarshmello(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

