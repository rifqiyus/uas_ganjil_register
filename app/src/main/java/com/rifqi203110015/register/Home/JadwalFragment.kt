//nama paket
package com.rifqi203110015.register.Home

//mengimport kelas yang dibutuhkan
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rifqi203110015.register.Home.Adapter.JadwalAdapter
import com.rifqi203110015.register.R
import com.rifqi203110015.register.ViewModel.JadwalViewModel
import com.rifqi203110015.register.local.DatabaseJadwal
import com.rifqi203110015.register.local.model.Jadwal
import kotlinx.android.synthetic.main.dialog_from_jadwal.view.*
import kotlinx.android.synthetic.main.fragment_jadwal.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

//membuat kelas JadwalFragment
class JadwalFragment : Fragment() {

    //variabel
    private var jadwalDatabase: DatabaseJadwal? = null
    lateinit var jadwalViewModel: JadwalViewModel

    private var dialogView: Dialog? = null
    //method onCreateView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // mengatur layout fragmen
        return inflater.inflate(R.layout.fragment_jadwal, container, false)
    }

    //method onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jadwalViewModel.showJadwalView()
        attachObserve()
        floatingActionButton.setOnClickListener {
            getshowAddDialog()
        }
    }

    //method onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jadwalViewModel = ViewModelProvider(this).get(JadwalViewModel::class.java)
        jadwalDatabase = context?.let { DatabaseJadwal.getInstance(it) }
    }

    //variabel privat getshowAddDialog
    private fun getshowAddDialog() {

        //variabel
        val dialog = AlertDialog.Builder(context)
        val view: View = layoutInflater.inflate(R.layout.dialog_from_jadwal, null)
        dialog.setView(view)
        view.btnSave.setOnClickListener {


            val pelajaran = view.editTextTextPersonName.text.toString()
            val keterangan = view.tvDesckipsi.text.toString()
            val tanggal = getDate()

            jadwalViewModel.addJadwalView(Jadwal(null, pelajaran, keterangan, tanggal))

        }
        view.imageView.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()

    }

    //method getDate untuk menampilkan tanggal
    private fun getDate(): String {
        val date: Date = Calendar.getInstance().time
        val formatter: DateFormat = SimpleDateFormat.getDateInstance()
        val formatDate: String = formatter.format(date)

        return formatDate

    }

    //method showJadwal menampilkan jadwal
    private fun showJadwal(it: List<Jadwal>?) {
        recyclerView.adapter = JadwalAdapter(it, object : JadwalAdapter.OnClickListener {
            //method delete memberikan opsi hapus jadwal
            override fun delete(item: Jadwal?) {
                AlertDialog.Builder(context).apply {
                    setTitle("hapus")
                    setMessage("yakin hapus Jadwal ?")
                    setCancelable(false)
                    setPositiveButton("yakin ") { dialogInterface, i ->
                        jadwalViewModel.deleteJadwalView(item!!)
                    }
                    setNegativeButton("batal") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }

            //method update mengupdate jadwal
            override fun update(item: Jadwal?) {
                getShowUpdateData(item)


            }

        })
    }

    //method getShowUpdateData menampilkan data yang telah diupdate
    private fun getShowUpdateData(item: Jadwal?) {

        val dialog = AlertDialog.Builder(context)
        val view: View = layoutInflater.inflate(R.layout.dialog_from_jadwal, null)
        dialog.setView(view)
        view.btnSave.text = "update"
        view.editTextTextPersonName.setText(item?.pelajaran)
        view.tvDesckipsi.setText(item?.keterangan)


        view.btnSave.setOnClickListener {
            val id = item?.id
            val matapelajran = view.editTextTextPersonName.text.toString()
            val keterangan = view.tvDesckipsi.text.toString()
            val date = getDate()
            jadwalViewModel.updateJadwalView(Jadwal(id, matapelajran, keterangan, date))

        }
        view.imageView.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()

    }


    private fun attachObserve() {
        jadwalViewModel.ShowJadwal.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showJadwal(it) })
        jadwalViewModel.isError.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showError(it) })
        jadwalViewModel.AddJadwal.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showAddJadwal(it) })
        jadwalViewModel.UpdateJadwal.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showUpdateJadwal(it) })
        jadwalViewModel.DeleteJadwal.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showDeleteJadwal(it) })
    }

    //method showError akan menampilkan notice jika terjadi error
    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    //method showAddJadwal menampilkan teks jadwal berhasil disimpan
    private fun showAddJadwal(

        it: Unit?) {
        dialogView?.dismiss()
        Toast.makeText(context, "Jadwal   pelajaran berhasil disimpan", Toast.LENGTH_SHORT).show()
        jadwalViewModel.showJadwalView()
    }

    //method showAddJadwal menampilkan teks jadwal berhasil diupdate
    private fun showUpdateJadwal(it: Unit?) {
        dialogView?.dismiss()
        Toast.makeText(context, "Jadwal pelajran berhasil diupdate", Toast.LENGTH_SHORT).show()
        jadwalViewModel.showJadwalView()
    }

    //method showAddJadwal menampilkan teks jadwal berhasil dihapus
    private fun showDeleteJadwal(it: Unit?) {
        Toast.makeText(context, "jadwal pelajaran  berhasil dihapus", Toast.LENGTH_SHORT).show()
        jadwalViewModel.showJadwalView()
    }
}