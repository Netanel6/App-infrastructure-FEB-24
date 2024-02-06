package com.netanel.hometest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.netanel.hometest.databinding.ActivityMainBinding
import com.netanel.hometest.home.view.DataState
import com.netanel.hometest.home.view.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var splashScreenStays = true
    private val delayTime = 800L

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splash.setKeepOnScreenCondition { splashScreenStays }
        Handler(Looper.getMainLooper()).postDelayed({ splashScreenStays = false }, delayTime)

        getCharacter()
        // Move to FragmentHome
        viewModel.dataResult.observe(this) {
            when (it) {
                DataState.Loading -> {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                }
                is DataState.Success -> {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                    Toast.makeText(this, it.data.toString(), Toast.LENGTH_SHORT).show()
                }

                is DataState.Error -> {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCharacter() {
        viewModel.getCharacters()
    }
}
