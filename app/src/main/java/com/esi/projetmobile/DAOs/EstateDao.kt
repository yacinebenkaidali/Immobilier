package com.esi.projetmobile.DAOs

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.esi.projetmobile.Model.Estate

@Dao
interface EstateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEstate(estate: Estate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEstateExist(estateList: MutableList<Estate>)

    @Delete
    fun deleteEstate(estate: Estate)

    @Query("SELECT * FROM Estate")
    fun getAllEstates(): LiveData<MutableList<Estate>>

    @Query("SELECT * FROM Estate WHERE id=:id")
    fun getEstateById(id: Int): Estate


    @Query("SELECT count(*) FROM Estate")
    fun getCount(): Int
}