package com.netanel.hometest.utils

import android.util.Log
import com.netanel.hometest.BuildConfig

/**
 * Created by netanelamar on 01/01/24.
 * NetanelCA2@gmail.com
 */
object Logger {
    fun info(
        tag: String = "info",
        message: String = "message",
    ) {
        if (!BuildConfig.DEBUG) return
        Log.i(tag, message)
    }
}
