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
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar        : MaterialToolbar
    private lateinit var navController  : NavController
    private lateinit var binding        : ActivityMainBinding
    private lateinit var navigationView : NavigationView
    private lateinit var drawerLayout   : DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.activityMainToolbar
        navigationView = binding.navView
        drawerLayout = binding.drawerLayout
        val navHost = supportFragmentManager.findFragmentById(binding.navHostFrag.id) as NavHostFragment
        navController = navHost.navController

        // Define AppBarConfiguration: Connect DrawerLayout with Navigation Component
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        // Connect Toolbar with NavController
        toolbar.setupWithNavController(navController, appBarConfiguration)

        // Connect NavigationView with NavController
        navigationView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (drawerLayout.isOpen)
            drawerLayout.close()
        else
            super.onBackPressed()
    }
}