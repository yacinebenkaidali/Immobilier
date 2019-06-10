package com.esi.projetmobile.adapter


import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.esi.projetmobile.R
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.etate_img_grid_item.view.*

class SliderAdapterExample(private val context: Context, private var realEstateImages: MutableList<String>) :
    SliderViewAdapter<SliderAdapterExample.SliderAdapterVH>() {
    override fun getCount(): Int = realEstateImages.size
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.etate_img_grid_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        viewHolder.imageViewBackground.setImageURI(Uri.parse(realEstateImages[position]))
//TODO here's where you do the magic
    }

    class SliderAdapterVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.slider_img
    }
}