package com.esi.projetmobile.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.esi.projetmobile.R
import com.esi.projetmobile.utils.getCompressedBitmap
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.etate_img_grid_item.view.*
import java.io.ByteArrayOutputStream

class SliderAdapter(private val context: Context, private var realEstateImages: MutableList<String>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    override fun getCount(): Int = realEstateImages.size
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.etate_img_grid_item, null)
        return SliderAdapterVH(inflate)
    }


    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val bytearrayoutputstream = ByteArrayOutputStream()
        val bitmap = getCompressedBitmap(realEstateImages[position], context)
        viewHolder.imageViewBackground.setImageBitmap(bitmap)
    }



    class SliderAdapterVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.slider_img
    }
}