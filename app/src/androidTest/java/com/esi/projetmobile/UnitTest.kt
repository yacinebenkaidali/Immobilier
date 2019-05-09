package com.esi.projetmobile

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.esi.projetmobile.DAOs.EstateDao
import com.esi.projetmobile.DAOs.OwnerDao
import com.esi.projetmobile.Model.Estate
import com.esi.projetmobile.Model.Owner
import com.esi.projetmobile.Utils.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class UnitTest {
    private val appContext = InstrumentationRegistry.getTargetContext()
    private var app: AppDatabase? = null
    private var ownerDao: OwnerDao? = null
    private var estateDao: EstateDao? = null

    @Before
    fun createDb() {
        app = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java).build()
        ownerDao = app!!.ownerDao()
        estateDao= app!!.estateDao()
    }

    @After
    fun closeDb() {
        app!!.close()
    }

    @Test
    fun createAndInsertInDbOwner() {
        val list = mutableListOf(
            Owner(1, "yacine", "0845pppppp"),
            Owner(2, "mido", "223123")
        )
        ownerDao!!.insertAllOwners(list)
        val count = ownerDao!!.getCount()
        assertEquals(2, count)
    }

    @Test
    fun createAndInsertEstates() {
        val list = mutableListOf(
            Estate(1, "yacine", "NIce", 13.3, "geo:37.7749,-122.4194", Date(), 1),
            Estate(2, "mido", "NIce", 146.2, "geo:37.7749,-122.4194", Date(), 2)
        )
        estateDao!!.insertEstateExist(list)
        val count= estateDao!!.getCount()
        assertEquals(count,2)
    }

}
