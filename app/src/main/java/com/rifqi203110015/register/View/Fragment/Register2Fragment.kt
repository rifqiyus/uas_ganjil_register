//nama paket
package com.rifqi203110015.register.View.Fragment

//mengimport kelas yang dibutuhkan
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rifqi203110015.register.R
import com.rifqi203110015.register.ViewModel.LoginViewModel
import com.rifqi203110015.register.local.model.User
import kotlinx.android.synthetic.main.fragment_register2.*

//kelas Register2Fragment mengatur fragment untuk Register
class Register2Fragment : Fragment(), View.OnClickListener {

    //variabel
    var get_name: String? = null
    var get_email: String? = null
    lateinit var navController: NavController
    lateinit var strusernname: String
    lateinit var stremail: String
    lateinit var loginViewModel: LoginViewModel

    //method onCreateView mengatur layout pada kelas ini
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register2, container, false)
    }

    //method onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        get_name = arguments?.getString("name")
        get_email = arguments?.getString("email")

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    }

    //method onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnsubmit.setOnClickListener(this)
        tvStep2.setOnClickListener(this)
        tvInformasi.text =
            "hallo $get_name  untuk melanjutkan  register silahkan isi passsword dibawah"


    }

    //method onClick membuat action ketika button diklik
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnsubmit -> {


                if (tvPassword1.text.toString().isEmpty()) {
                    tvPassword1.error = "password harus di isi"

                } else if (tvPassword2.text.toString().isEmpty()) {
                    tvPassword2.error = " confirmasi password harus di isi"
                } else if(tvPassword1.text.toString().length <6 && tvPassword2.text.toString().length <= 6) {

                   Toast.makeText(context,"password minimal 6 huruf ",Toast.LENGTH_SHORT).show()

                    }
                else {
                    if (tvPassword1.text.toString().equals(tvPassword2.text.toString())) {
                        val bundle: Bundle = bundleOf(
                            "name" to get_name,
                            "email" to get_email,
                            "password" to tvPassword1.text.toString()
                        )

                        get_name?.let {
                            get_email?.let { it1 ->
                                loginViewModel.addUserView(User( null,
                                    it,
                                    it1,
                                    tvPassword1.text.toString(),
                                    tvPassword2.text.toString()
                                )

                                )
                            }
                        }
                        Toast.makeText(context,"data telah di tambahkan ",Toast.LENGTH_SHORT).show()
                        navController.navigate(R.id.resultFragment, bundle)

                    }
                    else{
                        Toast.makeText(context,"password tidak sama coba lagi",Toast.LENGTH_SHORT).show()
                    }
                }

            }
            R.id.tvStep2 ->   activity?.onBackPressed()



        }
    }

}