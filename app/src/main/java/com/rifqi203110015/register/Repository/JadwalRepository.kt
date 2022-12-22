//nama paket
package com.rifqi203110015.register.Repository

//megimport kelas yang dibutuhkan
import android.annotation.SuppressLint
import android.content.Context
import com.rifqi203110015.register.local.DatabaseJadwal
import com.rifqi203110015.register.local.model.Jadwal
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

//kelas JadwalRepository untuk menyimpan data jadwal
class JadwalRepository(context: Context) {

    private var databaseConfig : DatabaseJadwal? = null

    init {
        databaseConfig = DatabaseJadwal.getInstance(context)
    }


    @SuppressLint("CheckResult")
    //method showJadwal akan digunakan untuk menampilkan jadwal
    fun showJadwal(responHandler: (List<Jadwal>?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable() { databaseConfig?.jadwalDao()?.getAll() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 -> responHandler(it1) }
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    //method addDataJadwal untuk menambah data jadwal
    fun addDataJadwal(
   item: Jadwal,
        responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.jadwalDao()?.insert(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    //method addDataJadwal untuk mengupdate data jadwal
    fun updateJadwal(item: Jadwal, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.jadwalDao()?.update(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    //method addDataJadwal untuk menghapus data jadwal
    fun deleteJadwal(item: Jadwal, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.jadwalDao()?.delete(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }
}

