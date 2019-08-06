package com.appstreet.base.core

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.appstreet.base.R

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
    }






}