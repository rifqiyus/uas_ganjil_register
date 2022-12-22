//nama paket
package com.rifqi203110015.register.Home.Adapter

//mengimport kelas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifqi203110015.register.R
import com.rifqi203110015.register.local.model.Jadwal
import kotlinx.android.synthetic.main.item_jadwal.view.*

//kelas JadwalAdapter
class JadwalAdapter(
    private val data: List<Jadwal>?,
    val itemClick: OnClickListener
) :
    RecyclerView.Adapter<JadwalAdapter.ViewHolder>() {


    //membuat kelas ViewHolder
    class ViewHolder(
        val view: View,
        val itemClick: OnClickListener

    ) :
        //membuat elemen RecyclerView
        RecyclerView.ViewHolder(view) {
        fun bind(item: Jadwal?) {
            view.tvDate.text = item?.date
            view.tvJudulJadwal.text = item?.pelajaran
            view.tvKeterangan.text = item?.keterangan
            view.imageButton.setOnClickListener {
                itemClick.update(item)

            }
            //membuat action ketika image button ditekan
            view.imageButton2.setOnClickListener {
                itemClick.delete(item)
            }
        }
    }

    //membuat method onCreateViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal, parent, false)
        return ViewHolder(view, itemClick)
    }

    //membuat method getItemCount untuk menghitung item
    override fun getItemCount(): Int = data?.size ?: 0

    //membuat method onBindViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Jadwal? = data?.get(position)
        holder.bind(item)
    }

    //membuat interface OnClickListener
    interface OnClickListener {
        fun update(item: Jadwal?)
        fun delete(item: Jadwal?)
    }


}