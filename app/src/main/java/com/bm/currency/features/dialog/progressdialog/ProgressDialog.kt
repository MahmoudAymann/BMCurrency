package com.bm.currency.features.dialog.progressdialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.bm.currency.R

/**
 * Created by Mahmoud Ayman on 15/09/2023.
 * Email: mahmoud_aymann@outlook.com.
 */
class ProgressDialog(private val activity: Activity, private val dismissible: Boolean = false) {

    private lateinit var dialog: Dialog

    private fun showDialog() {
        if (!isShown()) {
            dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(dismissible)
            dialog.setContentView(R.layout.dialog_progress)
            try {
                dialog.show()
            } catch (e: Exception) {
                dialog.dismiss()
            }
        } else
            dialog.dismiss()
    }

    private fun hideDialog() {
        if (isShown())
            dialog.dismiss()
    }

    fun showProgressDialog(show: Boolean) {
        if (show)
            showDialog()
        else
            hideDialog()
    }

    private fun isShown(): Boolean = this::dialog.isInitialized && dialog.isShowing

}