package com.esi.projetmobile.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esi.projetmobile.Adapter.RealEstateAdapter.ViewHolder
import com.esi.projetmobile.Model.RealEstate
import com.esi.projetmobile.R
import kotlinx.android.synthetic.main.realestate_item.view.*

class RealEstateAdapter(realEstateList: MutableList<RealEstate>, context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    private var realEstateList: MutableList<RealEstate> = realEstateList
    private lateinit var context: Context

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.realestate_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return realEstateList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val realEstate=realEstateList[p1]
        p0.ownerName.text=realEstate.owner
        p0.squareFootage.text=realEstate.squareFootage.toString()
       // p0.realEstatewImg.setI=realEstate.owner
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var ownerName=itemView.owner_name
            var squareFootage=itemView.square_foot
            var realEstatewImg=itemView.realestate_image
    }
}