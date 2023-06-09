package com.example.leafdoctorapp.util

import android.location.Geocoder.GeocodeListener
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.developer.kalert.KAlertDialog
import com.example.leafdoctorapp.R
import java.text.SimpleDateFormat
import java.util.Locale


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun AppCompatActivity.showSuccessDialog(
    title: String? = "Success!",
    message: String? = "",
    onClick: () -> Unit?
) {
    KAlertDialog(this, KAlertDialog.SUCCESS_TYPE)
        .setTitleText(title)
        .setContentText(message)
        .setConfirmClickListener("OK") {
            it.cancel()
            onClick.invoke()
        }
        .show()
}

fun AppCompatActivity.showErrorDialog(
    title: String? = "Error!",
    message: String? = "",
    buttonText: String? = "Ok",
    onClick: () -> Unit?
) {
    KAlertDialog(this, KAlertDialog.ERROR_TYPE)
        .setTitleText(title)
        .setContentText(message)
        .setConfirmClickListener(buttonText) {
            it.cancel()
            onClick.invoke()
        }
        .show()
}

fun Fragment.showErrorDialog(
    title: String? = "Error!",
    message: String? = "",
    buttonText: String? = "Ok",
    onClick: () -> Unit?
) {
    KAlertDialog(requireContext(), KAlertDialog.ERROR_TYPE)
        .setTitleText(title)
        .setContentText(message)
        .setConfirmClickListener(buttonText) {
            it.cancel()
            onClick.invoke()
        }
        .show()
}

fun ImageView.loadUrl(url: String) {
    val glideImage = Glide.with(this)
    glideImage.clear(this)
    glideImage.load(url).into(this)
}

fun String.toDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)

}