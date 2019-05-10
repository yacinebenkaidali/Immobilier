package com.esi.projetmobile.DAOs

import android.arch.persistence.room.*
import com.esi.projetmobile.Model.Image

@Dao
interface ImagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(image: Image)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllImages(imageList: MutableList<Image>)

    @Delete
    fun deleteImage(img: Image)

    @Query("SELECT * FROM image")
    fun getAllImages(): MutableList<Image>

    @Query("SELECT * FROM Image WHERE id=:id")
    fun getImageById(id: Int): Image

    @Query("select count(*) from image")
    fun getCount():Int
}