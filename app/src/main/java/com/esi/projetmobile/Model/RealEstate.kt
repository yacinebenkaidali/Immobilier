package com.esi.projetmobile.Model

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

class RealEstate() : Parcelable {

    var id: Int = 0
    var owner: String = ""
    var condition: String = ""
    var squareFootage: Double = 0.0
    var coordinates: String = ""
    var images = mutableListOf<String>()

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        owner = parcel.readString()!!
        condition = parcel.readString()!!
        squareFootage = parcel.readDouble()
        coordinates = parcel.readString()!!
        parcel.readStringList(images)
    }

    constructor(
        id: Int,
        owner: String,
        condition: String,
        squareFootage: Double,
        coordinates: String
    ) : this() {

        this.id = id
        this.owner = owner
        this.condition = condition
        this.squareFootage = squareFootage
        this.coordinates = coordinates

    }

    constructor(
        id: Int,
        owner: String,
        condition: String,
        squareFootage: Double,
        coordinates: String,
        tmpList: MutableList<String>
    ) : this() {
        this.id = id
        this.owner = owner
        this.condition = condition
        this.squareFootage = squareFootage
        this.coordinates = coordinates
        Log.i("God", "About to do images")
        this.images = tmpList
        Log.i("God", "Done with Images")
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(owner)
        parcel.writeString(condition)
        parcel.writeDouble(squareFootage)
        parcel.writeString(coordinates)
        parcel.writeStringList(images)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RealEstate> {
        override fun createFromParcel(parcel: Parcel): RealEstate {
            return RealEstate(parcel)
        }

        override fun newArray(size: Int): Array<RealEstate?> {
            return arrayOfNulls(size)
        }
    }

}