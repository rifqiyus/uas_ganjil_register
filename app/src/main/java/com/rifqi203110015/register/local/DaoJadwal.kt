package com.rifqi203110015.register.local

import androidx.room.*
import com.rifqi203110015.register.local.model.Jadwal

@Dao
interface DaoJadwal {

    @Query("SELECT * FROM jadwal ")
    fun getAll(): List<Jadwal>

    @Insert
    fun insert(jadwal: Jadwal)

    @Update
    fun update(jadwal: Jadwal)

    @Delete
    fun delete(jadwal: Jadwal)


}