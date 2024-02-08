package com.netanel.hometest

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.netanel.hometest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private var splashScreenStays = true
    private val delayTime = 800L

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({ splashScreenStays = false }, delayTime)
        splash.setKeepOnScreenCondition { splashScreenStays }

        initializeNavController()
    }

    private fun initializeNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) { }

    @SuppressLint("MissingSuperCall")
    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("super.onBackPressed()", "androidx.appcompat.app.AppCompatActivity"),
    )
    override fun onBackPressed() {
        val currentFragment = navController.currentDestination?.id
        /*if (currentFragment == R.id.someFragment) {
            super.onBackPressed()
        }*/
    }
}
