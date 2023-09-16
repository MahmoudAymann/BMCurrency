package com.bm.currency.core.extensions

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView

/**
 * Created by Mahmoud Ayman on 10/09/2023.
 * Email: mahmoud_aymann@outlook.com.
 */

typealias InflateFragment<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
typealias InflateActivity<T> = (LayoutInflater) -> T

fun AppCompatActivity.initNavController(
    @IdRes fragmentResId: Int,
    @NavigationRes navGraphRes: Int? = null,
    @IdRes startDestinationRes: Int? = null,
    bundle: Bundle? = null
): NavController {
    val navHostFragment = supportFragmentManager.findFragmentById(fragmentResId) as NavHostFragment
    val navController = navHostFragment.navController
    if (navGraphRes != null && startDestinationRes != null) {
        val navGraph = navController.navInflater.inflate(navGraphRes)
        navGraph.setStartDestination(startDestinationRes)
        navController.setGraph(navGraph, bundle)
    }
    return navController
}

fun Fragment.initNavController(
    @IdRes fragmentResId: Int,
    @NavigationRes navGraphRes: Int? = null,
    @IdRes startDestinationRes: Int? = null,
    bundle: Bundle? = null
): NavController {
    val navHostFragment = childFragmentManager.findFragmentById(fragmentResId) as NavHostFragment
    val navController = navHostFragment.navController
    if (navGraphRes != null && startDestinationRes != null) {
        val navGraph = navController.navInflater.inflate(navGraphRes)
        navGraph.setStartDestination(startDestinationRes)
        navController.setGraph(navGraph, bundle)
    }
    return navController
}

fun Context.showToast(message: String?) = message?.let {
    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(@StringRes messageRes: Int?) = messageRes?.let {
    showToast(getString(it))
}


fun EditText.setDefaultTextWhenUnFocused(input: String, onAddedValueCallBack:()->Unit={}) {
    setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            // Check if the input is empty
            val text = text?.toString()
            if (text.isNullOrBlank()) {
                // If empty, set the default value
                setText(input)
                onAddedValueCallBack.invoke()
            }
        }
    }
}

fun EditText?.asAutoCompleteSetListToAdapter(
    context: Context,
    list: List<String>,
    ignoreFirstIndex: Boolean = false,
    onFirstIndexClick: () -> Unit = {},
    onItemSelected: (String) -> Unit = {}
) {
    if (this == null) return
    val adapter = ArrayAdapter(
        context, R.layout.simple_list_item_1, list
    )
    val et = (this as MaterialAutoCompleteTextView)
    et.setAdapter(adapter)
    et.setOnItemClickListener { parent, _, position, _ ->
        if (ignoreFirstIndex && position == 0) {
            onFirstIndexClick.invoke()
            return@setOnItemClickListener
        }
        val selectedItem = parent.getItemAtPosition(position) as String
        onItemSelected.invoke(selectedItem)
    }
}