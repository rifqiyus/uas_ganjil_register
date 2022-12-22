//nama paket
package com.rifqi203110015.register.ViewModel

//mengimport kelas yang dibutuhkan
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rifqi203110015.register.Repository.JadwalRepository
import com.rifqi203110015.register.local.DatabaseJadwal
import com.rifqi203110015.register.local.model.Jadwal

//membuat kelas JadwalViewModel
class JadwalViewModel(application: Application) : AndroidViewModel(application) {

    //membuat variabel
    private var databaseJadwal: DatabaseJadwal? = null
    val repository = JadwalRepository(application.applicationContext)
    var context: Context = application

    var ShowJadwal = MutableLiveData<List<Jadwal>?>()
    var isError = MutableLiveData<Throwable>()
    var AddJadwal = MutableLiveData<Unit>()
    var UpdateJadwal = MutableLiveData<Unit>()
    var DeleteJadwal = MutableLiveData<Unit>()

    //method showJadwalView menampilkan jadwal
    fun showJadwalView() {
        repository.showJadwal({
            ShowJadwal.value = it
        }, {
            isError.value = it
        })
    }

    //method addJadwalView menampilkan menambahkan jadwal
    fun addJadwalView(item: Jadwal){

        if (item.pelajaran?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else if (item.keterangan?.isEmpty()!!){
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else {
            repository.addDataJadwal(item, {

                AddJadwal.value = it
            }, {
                isError.value = it
            })
        }

    }

    //method updateJadwalView mengupdate jadwal
    fun updateJadwalView(item: Jadwal) {
        if (item.pelajaran?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else if (item.keterangan?.isEmpty()!!){
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else {

            repository.updateJadwal(item, {
                UpdateJadwal.value = it
            }, {
                isError.value = it
            })
        }
    }

    //method deleteJadwalView menghapus jadwal
    fun deleteJadwalView(item: Jadwal) {
        repository.deleteJadwal(item, {
            DeleteJadwal.value = it
        }, {
            isError.value = it
        })
    }


}