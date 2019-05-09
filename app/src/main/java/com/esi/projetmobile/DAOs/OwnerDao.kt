package com.esi.projetmobile.DAOs

import android.arch.persistence.room.*
import com.esi.projetmobile.Model.Owner

@Dao
interface OwnerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOwner(owner: Owner)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllOwners(ownerList: MutableList<Owner>)

    @Delete
    fun deleteOwner(owner: Owner)

    @Query("SELECT * FROM owner")
    fun getAllOwners(): MutableList<Owner>

    @Query("SELECT * FROM owner WHERE id=:id")
    fun getOwnerById(id: Int): Owner

    @Query("select count(*) from owner")
    fun getCount(): Int
}