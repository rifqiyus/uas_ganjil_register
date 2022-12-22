//nama paket
package com.rifqi203110015.register.View.Fragment

//mengimport kelas yang dibutuhkan
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rifqi203110015.register.R
import com.rifqi203110015.register.View.MainActivity
import kotlinx.android.synthetic.main.fragment_result.*

//kelas ResultFragment mengatur fragment untuk Register
class ResultFragment : Fragment() {

    //variabel
    var get_name: String? = null
    var get_email: String? = null

    val resultFragment: FragmentManager
        get() {
            TODO()
        }
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    //method onCreate mengambil data nama dan email
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        get_name = arguments?.getString("name")
        get_email = arguments?.getString("email")

    }

    //method onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        tvEmailResult.text = get_name
        tvidResult.text = get_email
        btnBackLogin.setOnClickListener(View.OnClickListener {

            //variabel intent untuk berpindah activity
            val intent = Intent(context, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)


        })
    }


}

