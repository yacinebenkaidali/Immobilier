package com.esi.projetmobile.model

class Owner() {
    var id:Int =0
    var name:String=""
    var phone:String=""
    constructor(id:Int,name:String,phone:String) :this(){
        this.id=id
        this.name=name
        this.phone=phone
    }
}