package com.bm.currency.core.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.bm.currency.R
import com.bm.currency.core.extensions.InflateFragment
import com.bm.currency.core.extensions.showToast
import com.bm.currency.features.activity.MainActivity

open class BaseFragment<VB : ViewBinding>(
    private val inflate: InflateFragment<VB>
) : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    open val backToolbarTitle: String? = null

    open fun onFragmentCreated() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        onFragmentCreated()
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showToast(message: String?) = message?.let {
        requireContext().showToast(it)
    }

    fun getStringFromArgs(key: String, defaultValue: String? = null): String? {
        return arguments?.getString(key) ?: defaultValue
    }

    fun NavController.navToFragment(
        @IdRes fragmentResId: Int,
        bundle: Bundle = bundleOf(),
        safeNavigation: Boolean = false
    ) {
        if (safeNavigation) {
            if (this.currentDestination?.id != fragmentResId) {
                this.navigate(fragmentResId, bundle)
            }
        } else
            this.navigate(fragmentResId, bundle)
    }

}