package com.aggreyah.globotour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aggreyah.globotour.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar        : MaterialToolbar
    private lateinit var navController  : NavController
    private lateinit var binding        : ActivityMainBinding
    private lateinit var bottomNavView  : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.activityMainToolbar
        val navHost = supportFragmentManager.findFragmentById(binding.navHostFrag.id) as NavHostFragment
        navController = navHost.navController
        bottomNavView = binding.bottomNavView

        // Define AppBarConfiguration
        val topLevelDestinations = setOf(R.id.fragmentCityList, R.id.fragmentFavoriteList)
        val appBarConfiguration = AppBarConfiguration(topLevelDestinations)

        // Connect Toolbar with NavController
        toolbar.setupWithNavController(navController, appBarConfiguration)

        // Connect BottomNavigationView with NavController
        bottomNavView.setupWithNavController(navController)
    }
}