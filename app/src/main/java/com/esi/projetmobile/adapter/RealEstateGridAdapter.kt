package com.esi.projetmobile.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esi.projetmobile.R
import kotlinx.android.synthetic.main.etate_img_grid_item.view.*

class RealEstateGridAdapter(private var realEstateImages: MutableList<String>) :
    RecyclerView.Adapter<RealEstateGridAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val view = LayoutInflater.from(p0.context).inflate(R.layout.etate_img_grid_item, p0, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val estateImg = realEstateImages[p1]
        p0.realEstatewImg.setImageURI((Uri.parse(estateImg)))
    }


    override fun getItemCount(): Int {
        return realEstateImages.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var realEstatewImg = itemView.estate_img_list!!
    }
}