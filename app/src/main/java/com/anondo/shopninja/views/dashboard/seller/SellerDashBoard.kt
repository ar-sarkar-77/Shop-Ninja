package com.anondo.shopninja.views.dashboard.seller

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.anondo.shopninja.R
import com.anondo.shopninja.databinding.ActivitySellerDashBoardBinding
import com.anondo.shopninja.views.starter.MainActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerDashBoard : AppCompatActivity() {


    @Inject
    lateinit var auth : FirebaseAuth

    private lateinit var bindng : ActivitySellerDashBoardBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bindng = ActivitySellerDashBoardBinding.inflate(layoutInflater)
        setContentView(bindng.root)

    /*    bindng.dashAnimationView.postDelayed({
            bindng.dashAnimationView.visibility = View.GONE
        } , 4000)
     */

        navController = findNavController(R.id.fragmentHostSellerDashboard)

        bindng.sellerBottomNavigationView.setupWithNavController(navController)

        val appbarConfig = AppBarConfiguration(setOf(
            R.id.myProductFragment,
            R.id.uploadProductFragment,
            R.id.sellerProfileFragment
        ))

        setupActionBarWithNavController(navController , appbarConfig)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu , menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_report->{
                Toast.makeText(this , "Report" , Toast.LENGTH_SHORT).show()
            }
            R.id.menu_setting->{
                Toast.makeText(this , "Setting" , Toast.LENGTH_SHORT).show()
            }
            R.id.menu_logout->{

                auth.signOut()
                startActivity(Intent(this , MainActivity::class.java))
                finish()

            }
        }

        return super.onOptionsItemSelected(item)
    }

}