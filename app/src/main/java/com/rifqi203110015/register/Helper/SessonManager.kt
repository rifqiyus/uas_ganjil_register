//nama paket
package com.rifqi203110015.register.Helper

//mengimport kelas
import android.content.Context
import android.content.SharedPreferences

//kelas SessonManager
class SessonManager(var context: Context){

    //membuat variabel
    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var PREF_NAME = "LOGIN"

    var ISLOGIN = "isLogin"
    var USERNAME = "username"
    var PASSWORD = "password"

    init {
        pref = context.getSharedPreferences(PREF_NAME, 0)
        editor = pref?.edit()
    }

    //variabel login
    var login: Boolean?
        get() = pref?.getBoolean(ISLOGIN, false)
        set(login) {
            editor?.putBoolean(ISLOGIN, true)
            editor?.commit()
        }

    //variabel username
    var username: String?
        get() = pref?.getString(USERNAME, "")
        set(username) {
            editor?.putString(USERNAME, username)
            editor?.commit()
        }

    //variabel password
    var password: String?
        get() = pref?.getString(PASSWORD, "")
        set(password) {
            editor?.putString(PASSWORD, password)
            editor?.commit()
        }

    //variabel logout
    fun logOut() {
        editor?.clear()
        editor?.commit()
    }

}