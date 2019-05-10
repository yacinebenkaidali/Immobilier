package com.esi.projetmobile.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.net.Uri

@Entity(
    tableName = "Image", foreignKeys = [ForeignKey(
        entity = Estate::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("estate_id")
    )]
)
class Image(
    @PrimaryKey
    var id: Int,
    var uri: String,
    @ColumnInfo(name = "estate_id")
    var estateId: Int
)
