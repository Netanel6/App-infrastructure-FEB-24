package com.netanel.hometest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.netanel.hometest.home.view.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var splashScreenStays = true
    private val delayTime = 800L

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        splash.setKeepOnScreenCondition { splashScreenStays }
        Handler(Looper.getMainLooper()).postDelayed({ splashScreenStays = false }, delayTime)

        // TODO: Move logic  to FragmentHome
        getCharacter()
    }

    private fun getCharacter() {
        lifecycleScope.launch {
            viewModel.getCharacters()
        }
    }
}
