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
        childColumns = arrayOf("estate_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
class Images(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "estate_id")
    var estateId: Int,
    var uri: Uri
)
