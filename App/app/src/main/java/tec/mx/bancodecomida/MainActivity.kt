package tec.mx.bancodecomida

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import tec.mx.bancodecomida.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //Using navigation controller in order to facilitate the work of building the app
    //The navController is the class that manage all fragment interactions
    private lateinit var navController : NavController
    //Using binding library in order to avoid using getElementById
    private lateinit var binding: ActivityMainBinding
    //Navigation Bar
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //Declare the navHostFragment that has the destinations of our navigation component
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        //Declaring the bottom navigation
        //IMPORTANT: We pass the amount of fragments we choose, in this
        //           case we pass the screens: feed, map, milestones and settings
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.feedFragment, R.id.mapaFragment, R.id.milestonesFragment, R.id.settingsFragment)
        )

        //We're enabling the support of the action bar that it will be transformed
        // into nav bar
        setSupportActionBar(binding.toolbar)
        //This method used to manage the navigation drawer icon and toolbar too.
        setupActionBarWithNavController(navController, appBarConfiguration)
        //The toolbar will be updated when the destination changes
        binding.bottomNav.setupWithNavController(navController)


    }

    //We're connecting AppCompatActivity with NavigationUI, this means
    // that we're connecting the top part of our app (title, hamburger menu, etc)
    // with the navigator, so it's just like magic!
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}