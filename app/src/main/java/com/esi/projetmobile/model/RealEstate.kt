package com.esi.projetmobile.model

import android.os.Parcel
import android.os.Parcelable

class RealEstate() : Parcelable {
    var id: Int = 0
    var owner: String = ""
    var wilaya: String = ""
    var squareFootage: Double = 0.0
    var coordinates: String = ""
    var type: String = ""
    var phone: String = ""
    var date:Long=0L
    var images = mutableListOf<String>()

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        owner = parcel.readString()!!
        wilaya = parcel.readString()!!
        squareFootage = parcel.readDouble()
        coordinates = parcel.readString()!!
        type = parcel.readString()!!
        phone = parcel.readString()!!
        date = parcel.readLong()
        parcel.readStringList(images)
    }

    constructor(
        id: Int,
        owner: String,
        wilaya: String,
        squareFootage: Double,
        coordinates: String,
        type: String,
        phone: String,
        long: Long,
        tmpList: MutableList<String>
    ) : this() {
        this.id = id
        this.owner = owner
        this.wilaya = wilaya
        this.squareFootage = squareFootage
        this.coordinates = coordinates
        this.images = tmpList
        this.type=type
        this.phone=phone
        this.date=long
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(owner)
        parcel.writeString(wilaya)
        parcel.writeDouble(squareFootage)
        parcel.writeString(coordinates)
        parcel.writeString(type)
        parcel.writeString(phone)
        parcel.writeLong(date)
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