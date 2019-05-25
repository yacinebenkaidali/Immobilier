package com.esi.projetmobile.Model

import android.arch.persistence.room.ColumnInfo
import java.util.*

class OwnerJoinEstate(
    var id: Int = 0,
    var name: String,
    var phone: String,
    var condition: String,
    var squareFootage: Double,
    var coordinates: String,
    var date: Date,
    var uri: String,
    @ColumnInfo(name = "estate_id")
    var estateId: Int
)