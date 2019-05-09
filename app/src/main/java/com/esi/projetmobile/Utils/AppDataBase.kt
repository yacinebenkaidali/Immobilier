package com.esi.projetmobile.Utils

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.esi.projetmobile.DAOs.EstateDao
import com.esi.projetmobile.DAOs.ImagesDao
import com.esi.projetmobile.DAOs.OwnerDao
import com.esi.projetmobile.Model.Estate
import com.esi.projetmobile.Model.Images
import com.esi.projetmobile.Model.Owner

@Database(entities = [Estate::class, Owner::class, Images::class], version = 1)
@TypeConverters(DateConverter::class, UriConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun estateDao(): EstateDao
    abstract fun imagesDao(): ImagesDao
    abstract fun ownerDao(): OwnerDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "immobilier.db").build()
    }
}