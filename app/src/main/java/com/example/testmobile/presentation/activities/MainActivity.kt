package com.example.testmobile.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.testmobile.R
import com.example.testmobile.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mNavController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbarHome)

        // FragmentContainer
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        mNavController = navHostFragment.navController
        // NavigationUI.setupActionBarWithNavController(this, mNavController)

        // Set Fragment don't display home arrow
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment
            )
        )
        setupActionBarWithNavController(mNavController, appBarConfiguration)
    }

    // Active Home back action
    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp()
    }
}