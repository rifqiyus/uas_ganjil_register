//nama paket
package com.rifqi203110015.register.Repository

//mengimport kelas yang dibutuhkan
import android.annotation.SuppressLint
import android.content.Context
import com.rifqi203110015.register.Helper.SessonManager
import com.rifqi203110015.register.local.DatabaseJadwal
import com.rifqi203110015.register.local.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

//kelas LoginRepository menyimpan data login
class LoginRepository(context: Context) {

    //variabel
    private var databaseConfig: DatabaseJadwal? = null
    var sessonManager = SessonManager(context)

    init {
        databaseConfig = DatabaseJadwal.getInstance(context)
    }


    @SuppressLint("CheckResult")
    //method detailUser menyimpan detail data login user
    fun detailUser(
        username: String,
        password: String,
        responHandler: (User) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable {
            databaseConfig?.userDao()?.getLoginDetail(username, password)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 ->
                    responHandler(it1)
                    sessonManager.username = it1.username
                    sessonManager.login = true
                }
            }, {
                errorHandler(it)
            })

    }

    @SuppressLint("CheckResult")
    //method cekLoginRegister memvalidasi data login user
    fun cekLoginRegister(

        username: String,
        email: String,
        responHandler: (User) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable {
            databaseConfig?.userDao()?.getcekregister(username, email)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 ->
                    responHandler(it1)

                }
            }, {
                errorHandler(it)
            })

    }


    @SuppressLint("CheckResult")
    //method showUser menampilkan user
    fun showUser(responHandler: (List<User>?) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { databaseConfig?.userDao()?.getData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 -> responHandler(it1) }
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    //method insertUser untuk memasukkan data user baru
    fun insertUser(
        item: User, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable() {
            databaseConfig?.userDao()?.insert(item)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })


    }

}