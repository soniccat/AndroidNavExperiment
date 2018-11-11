/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.codelabs.navigation

import android.content.res.Resources
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

/**
 * A simple activity demonstrating use of a NavHostFragment with a navigation drawer.
 */
class MainActivity : AppCompatActivity() {
    private var navState: Bundle? = Bundle.EMPTY

    private var navController: NavController = NavController(this)
    private var drawerLayout: androidx.drawerlayout.widget.DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return


        // Set up Action Bar
        navController = host.navController
        //val customNavigator = MyNavigator(this, supportFragmentManager, R.id.my_nav_host_fragment)
        //navController.navigatorProvider.addNavigator(customNavigator)

        //setupActionBar(navController)

        //setupNavigationMenu(navController)

        setupBottomNavMenu(navController)

        navController.addOnNavigatedListener { _, destination ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }

            Toast.makeText(this@MainActivity, "Navigated to $dest",
                    Toast.LENGTH_SHORT).show()
            Log.d("NavigationActivity", "Navigated to $dest")
        }

        updateFragmentVisibility()
    }

    private fun currentNavController(): NavController {
        val id = if (isFirstPageActive()) R.id.my_nav_host_fragment else R.id.my_nav_host_fragment2

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(id) as NavHostFragment
        return host.navController
    }

    private fun isFirstPageActive() =
            findViewById<BottomNavigationView>(R.id.bottom_nav_view).selectedItemId == R.id.launcher_home

    private fun setupBottomNavMenu(navController: NavController) {
//        findViewById<BottomNavigationView>(R.id.bottom_nav_view)?.let { bottomNavView ->
//            NavigationUI.setupWithNavController(bottomNavView, navController)
//        }

        findViewById<BottomNavigationView>(R.id.bottom_nav_view)?.setOnNavigationItemSelectedListener({item ->
            //navController.navigate(item.getItemId())
            updateFragmentVisibility()
            true
        })
    }

    private fun updateFragmentVisibility() {
        val isFirstVisible = isFirstPageActive()
        val fragment1 = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment)
        val fragment2 = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment2)
        fragment1!!.view!!.visibility = if (isFirstVisible) View.VISIBLE else View.INVISIBLE
        fragment2!!.view!!.visibility = if (isFirstVisible) View.INVISIBLE else View.VISIBLE

        val newPrimary = if (isFirstVisible) fragment1 else fragment2
        supportFragmentManager.beginTransaction().setPrimaryNavigationFragment(newPrimary).commitAllowingStateLoss()
    }

    private fun setupNavigationMenu(navController: NavController) {
//        findViewById<NavigationView>(R.id.nav_view)?. let { navigationView ->
//            NavigationUI.setupWithNavController(navigationView, navController)
//        }
    }

    private fun setupActionBar(navController: NavController) {
        drawerLayout = findViewById(R.id.drawer_layout)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val retValue = super.onCreateOptionsMenu(menu)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        // The NavigationView already has these same navigation items, so we only add
        // navigation items to the menu here if there isn't a NavigationView
        if (navigationView == null) {
            menuInflater.inflate(R.menu.menu_overflow, menu)
            return true
        }
        return retValue
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Have the NavHelper look for an action or destination matching the menu
        // item id and navigate there if found.
        // Otherwise, bubble up to the parent.
        if (item.itemId == R.id.save_state) {
            val host: NavHostFragment = supportFragmentManager
                    .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
            val fragment: Fragment = host.childFragmentManager.getFragments().get(0)

            navState = navController.saveState()
            supportFragmentManager
            return true;
        } else if (item.itemId == R.id.restore_state) {
            navController.restoreState(navState)
            val navigator: FragmentNavigator = navController.navigatorProvider.getNavigator<FragmentNavigator.Destination, FragmentNavigator>(FragmentNavigator::class.java)

            val destination = navController.currentDestination as FragmentNavigator.Destination
            navigator.navigate(destination, destination.defaultArguments, null, null)
            return true;
        }

        return NavigationUI.onNavDestinationSelected(item,
                Navigation.findNavController(this, R.id.my_nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(drawerLayout,
                Navigation.findNavController(this, R.id.my_nav_host_fragment))
    }
}
