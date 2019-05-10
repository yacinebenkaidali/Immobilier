package com.esi.projetmobile.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "Estate", foreignKeys = [ForeignKey(
        entity = Owner::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("owner_id")
    )]
)
data class Estate(
    @PrimaryKey
    var id: Int,
    var owner: String,
    var condition: String,
    var squareFootage: Double,
    var coordinates: String,
    var date: Date,
    @ColumnInfo(name = "owner_id")
    var ownerId: Int
)