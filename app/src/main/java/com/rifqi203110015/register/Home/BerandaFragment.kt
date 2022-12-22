//nama paket
package com.rifqi203110015.register.Home

//mengimport kelas yang dibutuhkan
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rifqi203110015.register.Helper.SessonManager
import com.rifqi203110015.register.R
import com.rifqi203110015.register.View.MainActivity
import kotlinx.android.synthetic.main.fragment_beranda.*

//membuat kelas BerandaFragment untuk tampilan beranda
class BerandaFragment : Fragment() {

    //membuat variabel get_name
    var get_name: String? = null
    lateinit var navController: NavController

    //membuat method onCreateView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_beranda, container, false)

    }

    //membuat method onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        get_name = arguments?.getString("name")
    }

    //membuat method onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val sessonManager = SessonManager (requireContext())

        //membuat action ketika tombol ditekan
        btnLogout.setOnClickListener(View.OnClickListener {
            sessonManager.logOut()
            val intent = Intent(context, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
          startActivity(intent)
            activity?.finish()
        })


    }


}




