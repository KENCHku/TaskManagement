package kg.geeks.taskmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kg.geeks.taskmanagement.data.local.pref.Pref
import kg.geeks.taskmanagment.R
import kg.geeks.taskmanagment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var pref: Pref

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = Pref(this)

        initGeneralUI()
    }

    private fun initGeneralUI() {
        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNavView: BottomNavigationView = binding.bottomNavView

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)

        val fragmentsWithoutBars = setOf(
            R.id.taskFragment,
            R.id.onBoardFragment,
            R.id.authFragment
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (fragmentsWithoutBars.contains(destination.id)) {
                bottomNavView.isVisible = false
                supportActionBar?.hide()
            } else {
                bottomNavView.isVisible = true
                supportActionBar?.show()
            }
        }

        if (!pref.isBoardingSeen()) {
            navController.navigate(R.id.onBoardFragment)
        }

        if (FirebaseAuth.getInstance().currentUser == null){
            navController.navigate(R.id.authFragment)
        }
    }
}