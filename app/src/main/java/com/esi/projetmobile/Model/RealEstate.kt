package com.esi.projetmobile.Model

import android.graphics.Bitmap
import android.net.Uri

class RealEstate() {
    operator fun minus(real2: RealEstate): Double {
        return (this.squareFootage - real2.squareFootage)
    }

    var id: Int = 0
    var owner: String = ""
    var condition: String = ""
    var squareFootage: Double = 0.0
    var coordinates: String = ""
    var images = mutableListOf<Uri>()

    constructor(id: Int, owner: String, condition: String, squareFootage: Double, coordinates: String) : this() {
        this.id = id
        this.owner = owner
        this.condition = condition
        this.squareFootage = squareFootage
        this.coordinates = coordinates
    }


}