package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    // TODO if you are not going to use some dependency injection approach my suggestion is to avoid
    //  use of lateinit. In this particular case everything is clear with initialization, but imagine
    //  you have a really complex class and forgot to init your lateinit variable just by occasion as
    //  the result it will trigger [java.lang.RuntimeException] and your application will crash.
    //  In order to avoid it you can do:
    //  1. initialization in one place. If navController, appBarConfiguration will not be used outside of
    //  onCreate, so you can just initialize everything in onCreate
    //  2. use kotlin lazy {} delegate so the value will be computed only on the first access and cached
    //  after that. Example:
    //      private val navHostFragment by lazy {
    //        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    //    }
    //    private val navController by lazy {
    //        navHostFragment.navController
    //    }

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO Suggestion. It will be better to plant you Timber tree inside application class not activity.
        //  If you have single activity app it may be OK, but if you will call some Timber's logs out
        //  of you Activity it will not be triggered
        Timber.plant(Timber.DebugTree())
        setSupportActionBar(findViewById(R.id.toolbar))
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        //return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        return navController.navigateUp()
        //        || super.onSupportNavigateUp()
    }
    //TODO remove redundant line

}