package com.example.themoviedatabase.utils.extentions

import android.view.LayoutInflater
import android.view.View
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.themoviedatabase.utils.FragmentAutoClearedValueBinding

@Suppress("unused")
fun <T : ViewBinding> Fragment.viewBindingWithBinder(
    binder: (View) -> T
) = FragmentAutoClearedValueBinding(binder)

inline fun <T : ViewBinding> ComponentActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}