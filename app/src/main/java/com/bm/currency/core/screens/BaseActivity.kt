package com.bm.currency.core.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.bm.currency.core.extensions.InflateActivity

open class BaseActivity<B : ViewBinding>(
    private val inflate: InflateActivity<B>
) : AppCompatActivity() {

    val binding: B by lazy(LazyThreadSafetyMode.NONE) {
        inflate.invoke(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}