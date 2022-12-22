//nama paket
package com.rifqi203110015.register.Home

//mengimport kelas yang dibutuhkan
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.rifqi203110015.register.Helper.SessonManager
import com.rifqi203110015.register.R
import kotlinx.android.synthetic.main.activity_home.*

//membuat kelas HomeActivity
class HomeActivity : AppCompatActivity() {

    //membuat method onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val sessonManager = SessonManager(this)
        val nama = sessonManager.username

                title = "hello $nama"

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(buttonNavigation, navController)
    }
}