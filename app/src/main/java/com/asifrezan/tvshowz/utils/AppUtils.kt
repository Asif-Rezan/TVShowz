package com.asifrezan.tvshowz.utils
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.asifrezan.tvshowz.R


const val IMAGE_STORAGE_URL = "https://image.tmdb.org/t/p/w500"



fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}


fun showAlertDialog(context: Context)
{
    val dialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_layout, null)
    val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)
    val dialogMessage = dialogView.findViewById<TextView>(R.id.dialog_message)
    val dialogOkButton = dialogView.findViewById<Button>(R.id.dialog_ok_button)

    dialogTitle.text = "Network Error"
    dialogMessage.text = "The device you're trying to connect to is not currently online. Please check your internet connection."

    val alertDialogBuilder = AlertDialog.Builder(context)
    alertDialogBuilder.setView(dialogView)

    val alertDialog = alertDialogBuilder.create()
    alertDialog.show()

    dialogOkButton.setOnClickListener {
        alertDialog.dismiss()
    }
}