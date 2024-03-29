package com.thetrusttech.getacarparts.base

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    val getViewModelFactory: ViewModelFactory? by lazy {
        activity?.application?.let {
            ViewModelFactory(it)
        }
    }
}