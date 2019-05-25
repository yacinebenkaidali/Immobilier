package com.esi.projetmobile.DAOs

import android.arch.persistence.room.*
import com.esi.projetmobile.Model.Owner
import com.esi.projetmobile.Model.OwnerJoinEstate

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

    @Query("select Owner.*,owner_id,coordinates,condition,squareFootage,date,uri,estate_id   from Owner inner join estate on owner.id=estate.owner_id inner join image on estate.id=image.estate_id")
    fun getJoinedData(): MutableList<OwnerJoinEstate>
}