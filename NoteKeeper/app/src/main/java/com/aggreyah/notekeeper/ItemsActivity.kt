package com.aggreyah.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aggreyah.notekeeper.databinding.ActivityItemsBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class ItemsActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityItemsBinding

    private val noteLayoutManager by lazy{LinearLayoutManager(this)}

    private val noteRecyclerAdapter by lazy { NoteRecyclerAdapter(this, DataManager.notes) }

    private val courseLayoutManager by lazy { GridLayoutManager(this, 2) }

    private val courseRecyclerAdapter by lazy { CourseRecyclerAdapter(this, DataManager.courses.values.toList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarItems.toolbar)

        binding.appBarItems.fab.setOnClickListener { view ->
            startActivity(Intent(this, NoteActivity::class.java))
        }

        displayNotes()

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_items)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_notes, R.id.nav_courses
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_notes -> displayNotes()
                R.id.nav_courses -> displayCourses()
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun displayNotes() {
        binding.appBarItems.includedLayoutContent.listItems.layoutManager = noteLayoutManager
        binding.appBarItems.includedLayoutContent.listItems.adapter = noteRecyclerAdapter
        binding.navView.menu.findItem(R.id.nav_notes).isChecked = true
    }

    private fun displayCourses() {
        binding.appBarItems.includedLayoutContent.listItems.layoutManager = courseLayoutManager
        binding.appBarItems.includedLayoutContent.listItems.adapter = courseRecyclerAdapter
        binding.navView.menu.findItem(R.id.nav_courses).isChecked = true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_items)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        binding.appBarItems.includedLayoutContent.listItems.adapter?.notifyDataSetChanged()
    }


}