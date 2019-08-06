package com.appstreet.base.extension

import android.content.Context
import android.widget.Toast

fun Context.showLongToast(str: String) = Toast.makeText(this, str, Toast.LENGTH_LONG).show()
