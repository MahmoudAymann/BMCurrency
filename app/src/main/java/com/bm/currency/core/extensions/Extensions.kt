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
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject

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
inline fun <reified T> createJsonAdapter(): JsonAdapter<T> {
    val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory())
        .build()
    return moshi.adapter(T::class.java)
}
inline fun <reified T> T?.toHashMapParams(): HashMap<String, String?>? {
    if (this == null) return null
    val params by lazy<HashMap<String, String?>> { HashMap() }
    try {
        val jsonAdapter = createJsonAdapter<T>()

        JSONObject(jsonAdapter.toJson(this)).let {
            if (it.names() != null)
                for (i in 0 until it.names()!!.length()) {
                    params[it.names()!!.getString(i)] =
                        it[it.names()!!.getString(i)].toString() + ""
                }
        }
    } catch (e: Exception) {
        println(e)
    }
    return if (params.isEmpty()) null else params
}