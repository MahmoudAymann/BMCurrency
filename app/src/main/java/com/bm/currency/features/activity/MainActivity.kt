package com.bm.currency.features.activity

import android.os.Bundle
import androidx.navigation.NavController
import com.bm.currency.R
import com.bm.currency.core.extensions.initNavController
import com.bm.currency.core.screens.BaseActivity
import com.bm.currency.databinding.ActivityMainBinding

/**
 * Created by Mahmoud Ayman on 10/09/2023.
 * Email: mahmoud_aymann@outlook.com.
 */
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = initNavController(
            fragmentResId = R.id.navigation_main,
            navGraphRes = R.navigation.navigation_main,
            startDestinationRes = null,
            bundle = null
        )
    }


}