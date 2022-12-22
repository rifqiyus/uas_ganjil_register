//nama paket
package com.rifqi203110015.register.View.Fragment

//mengimport kelas yang dibutuhkan
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rifqi203110015.register.R
import com.rifqi203110015.register.ViewModel.LoginViewModel
import com.rifqi203110015.register.local.model.User
import kotlinx.android.synthetic.main.fragment_main.*

//kelas MainFragment
class MainFragment : Fragment(), View.OnClickListener {

    //variabel
    lateinit var navControler: NavController

    lateinit var loginViewModel: LoginViewModel


    lateinit var strUsername: String
    lateinit var strPassword: String

    //method onCreateView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)


    }

    //method onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    }

    //method onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navControler = Navigation.findNavController(view)
        attachObserve()
        btnRegister.setOnClickListener(this)
        btnLogin.setOnClickListener(this)


    }

    //method privat attachObserve
    private fun attachObserve() {

        loginViewModel.detailLogin.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { loginSucces(it) })
        loginViewModel.isError.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { loginError(it) })

    }

    //method loginError memunculkan notice ketika user belum terdaftar
    private fun loginError(it: Throwable) {
        Toast.makeText(
            context,
            "  username belum ada coba register terlebih dahulu",
            Toast.LENGTH_SHORT
        ).show()
    }

    //method loginSucces memvalidasi login user
    private fun loginSucces(it: User) {



        navControler.navigate(
            R.id.action_mainFragment_to_homeActivity3

        )
        activity?.finish()


    }

    //method onClick
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnRegister ->
                navControler.navigate(R.id.action_mainFragment_to_register1Fragment)

            R.id.btnLogin -> {
                strUsername = tvid.text.toString().trim()
                strPassword = tvPassword.text.toString().trim()
                loginViewModel.getLogin(strUsername, strPassword)
//                if (strUsername.isEmpty()) {
//
//                    tvid.error = " masukkan username"
//                } else if (strPassword.isEmpty()) {
//                    tvPassword.error = "masukkan password"
//                } else {
//
//
//
//                }
//


            }
        }
    }



}


