package com.esi.projetmobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.esi.projetmobile.R
import com.esi.projetmobile.adapter.RealEstateAdapter.ViewHolder
import com.esi.projetmobile.fragment.AdsFragementDirections
import com.esi.projetmobile.model.RealEstate
import com.esi.projetmobile.utils.getCompressedBitmap
import kotlinx.android.synthetic.main.realestate_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Comparator


class RealEstateAdapter(
    private var realEstateList: MutableList<RealEstate>,
    private var context: Context,
    val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ViewHolder>(), Filterable {
    private var realEstateListFiltered: MutableList<RealEstate> = realEstateList

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.realestate_item, p0, false)
        return ViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return realEstateListFiltered.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val realEstate = realEstateListFiltered[p1]
        p0.bind(realEstate)
    }


    interface OnItemClickListener {
        operator fun invoke(estate: RealEstate)
    }


    inner class ViewHolder(itemView: View, val clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var itemCard = itemView.brand_card!!
        var ownerName = itemView.owner_name!!
        var cityName = itemView.city_name!!
        var dateDis = itemView.date_dis!!
        var squareFootage = itemView.square_foot!!
        var realEstatewImg = itemView.realestate_image!!

        fun bind(realEstate: RealEstate) {

            val date = Date(realEstate.date)
            val fmt = SimpleDateFormat("yyyy-MM-dd")
            this.ownerName.text = realEstate.owner
            this.squareFootage.text = context.getString(R.string.area, realEstate.squareFootage.toString())
            val dateString = fmt.format(date)
            this.dateDis.text = dateString
            this.cityName.text = realEstate.wilaya
            if (realEstate.images.size != 0) {
                val bitmapRes = getCompressedBitmap(realEstate, context)
                this.realEstatewImg.setImageBitmap(bitmapRes)
            } else {
                this.realEstatewImg.setImageResource(R.drawable.skyscraper)
            }

            this.itemCard.setOnClickListener {
                //Navigation.findNavController(it).navigate(AdsFragementDirections.actionAdsToDetailFragment(realEstate))
                clickListener(realEstate)

            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                realEstateListFiltered = if (charString.isEmpty()) {
                    realEstateList
                } else {
                    val filteredList = mutableListOf<RealEstate>()
                    for (row in realEstateList) {
                        if (row.owner.toLowerCase().contains(charString.toLowerCase()) || row.wilaya.toLowerCase().contains(
                                charSequence
                            ) || (Date(row.date).toString().contains(charSequence))
                        ) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = realEstateListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                @Suppress("UNCHECKED_CAST")
                realEstateListFiltered = filterResults.values as MutableList<RealEstate>
                notifyDataSetChanged()
            }
        }
    }

    fun wilayaNameSort() {
        realEstateList.sortWith(Comparator { real1, real2 ->
            real1.wilaya.compareTo(real2.wilaya)
        })
    }

    fun squareFootSort() {
        realEstateList.sortWith(Comparator { real1, real2 ->
            (real1.squareFootage - real2.squareFootage).toInt()
        })
    }

    fun dateSort() {
        realEstateList.sortWith(Comparator { real1, real2 ->
            val date1 = Date(real1.date)
            val date2 = Date(real2.date)
            date1.compareTo(date2)
        })
    }
}