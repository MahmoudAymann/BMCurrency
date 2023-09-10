package com.bm.currency.core.extensions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

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
    val navHostFragment =
        supportFragmentManager.findFragmentById(fragmentResId) as NavHostFragment
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
    val navHostFragment =
        childFragmentManager.findFragmentById(fragmentResId) as NavHostFragment
    val navController = navHostFragment.navController
    if (navGraphRes != null && startDestinationRes != null) {
        val navGraph = navController.navInflater.inflate(navGraphRes)
        navGraph.setStartDestination(startDestinationRes)
        navController.setGraph(navGraph, bundle)
    }
    return navController
}