package com.esi.projetmobile.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Owner")
class Owner(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var phone: String
)