package com.esi.projetmobile.DAOs

import android.arch.persistence.room.*
import com.esi.projetmobile.Model.Images

@Dao
interface ImagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(owner: Images)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllImages(ownerList: MutableList<Images>)

    @Delete
    fun deleteImage(owner: Images)

    @Query("SELECT * FROM image")
    fun getAllImages(): MutableList<Images>

    @Query("SELECT * FROM Image WHERE id=:id")
    fun getImageById(id: Int): Images

}