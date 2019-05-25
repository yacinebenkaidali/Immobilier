package com.esi.projetmobile

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.esi.projetmobile.DAOs.EstateDao
import com.esi.projetmobile.DAOs.ImagesDao
import com.esi.projetmobile.DAOs.OwnerDao
import com.esi.projetmobile.Model.Estate
import com.esi.projetmobile.Model.Image
import com.esi.projetmobile.Model.Owner
import com.esi.projetmobile.Utils.AppDatabase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class UnitTest {
    private val appContext = InstrumentationRegistry.getTargetContext()
    private var app: AppDatabase? = null
    private var ownerDao: OwnerDao? = null
    private var estateDao: EstateDao? = null
    private var imagesDao: ImagesDao? = null

    @Before
    fun createDb() {
        app = AppDatabase.invoke(appContext)
        ownerDao = app!!.ownerDao()
        estateDao = app!!.estateDao()
        imagesDao = app!!.imagesDao()
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
        val count: MutableList<Owner> = ownerDao!!.getAllOwners()
        for (item in count) {
            Log.i("JUnit", item.id.toString())
        }
        assertEquals(2, count.size)
    }

    @Test
    fun createAndInsertEstates() {
        val list = mutableListOf(
            Estate(1, "california", "Nice", 13.3, "geo:37.7749,-122.4194", Date(), 1),
            Estate(2, "newyork", "Nice", 146.2, "geo:37.7749,-122.4194", Date(), 2)
        )
        estateDao!!.insertEstateList(list)
        val count = estateDao!!.getCount()
        assertEquals(count, 2)
    }

    @Test
    fun createAndInsertImages() {
        val list = mutableListOf(
            Image(1, "content://media/external/images/media/52", 1),
            Image(2, "content://media/external/images/media/53", 2)
        )
        imagesDao!!.insertAllImages(list)
        val count = imagesDao!!.getCount()
        assertEquals(2, count)
    }

    @Test
    fun getAllData() {
        val list = ownerDao!!.getJoinedData()
        assertNotNull(list)
    }
}
