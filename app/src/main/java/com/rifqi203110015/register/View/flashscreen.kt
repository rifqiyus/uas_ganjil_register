//nama paket
package com.rifqi203110015.register.View

//mengimport kelas yang dibutuhkan
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rifqi203110015.register.Helper.SessonManager
import com.rifqi203110015.register.Home.HomeActivity
import com.rifqi203110015.register.R

//membuat kelas flashscreen
class flashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashscreen)
        var sessonLogin = SessonManager(this)

        Handler().postDelayed(Runnable {

            if (sessonLogin.login ?: true) {

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                    finish()
            }

        }, 5000)
        title = ""


    }

    //method onDestroy
    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}