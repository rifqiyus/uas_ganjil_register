//nama paket
package com.rifqi203110015.register.local.model

//mengimport kelas yang dibutuhkan
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jadwal")
//kelas jadwal akan menyimpan data jadwal pelajaran
data class Jadwal(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "pelajaran")
    var pelajaran: String? = null,
    @ColumnInfo(name = "keterangan")
    var keterangan: String? = null,
    @ColumnInfo(name = "date")
    var date: String? = null
)