package com.esi.projetmobile.Model

import android.graphics.Bitmap

    class RealEstate {
        operator fun minus(real2: RealEstate): Double {
            return (this.squareFootage-real2.squareFootage)
        }

        var id: Int
        var owner: String
        var condition: String
        var squareFootage: Double
        var coordinates :String
        lateinit var images:MutableList<Bitmap>

        constructor(id: Int, owner: String, condition: String, squareFootage: Double,coordinates:String) {
            this.id = id
            this.owner = owner
            this.condition = condition
            this.squareFootage = squareFootage
            this.coordinates =coordinates
        }


}