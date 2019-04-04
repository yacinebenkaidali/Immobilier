package com.esi.projetmobile.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.esi.projetmobile.Adapter.RealEstateAdapter.ViewHolder
import com.esi.projetmobile.Model.RealEstate
import com.esi.projetmobile.R
import kotlinx.android.synthetic.main.realestate_item.view.*

class RealEstateAdapter(realEstateList: MutableList<RealEstate>, context: Context) :
    RecyclerView.Adapter<ViewHolder>(),Filterable {


    private var realEstateList: MutableList<RealEstate> = realEstateList
    private  var realEstateListFiltered :MutableList<RealEstate> = realEstateList
    private lateinit var context: Context

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.realestate_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return realEstateListFiltered.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val realEstate=realEstateListFiltered[p1]
        p0.ownerName.text=realEstate.owner
        p0.squareFootage.text=realEstate.squareFootage.toString()
       // p0.realEstatewImg.setI=realEstate.owner
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var ownerName=itemView.owner_name
            var squareFootage=itemView.square_foot
            var realEstatewImg=itemView.realestate_image
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    realEstateListFiltered = realEstateList
                } else {
                    val filteredList = mutableListOf<RealEstate>()
                    for (row in realEstateList) {
                        if (row.owner.toLowerCase().contains(charString.toLowerCase()) || row.condition.contains(charSequence)) {
                            filteredList.add(row)
                        }
                    }
                    realEstateListFiltered = filteredList
                }
                val filterResults = Filter.FilterResults()
                filterResults.values = realEstateListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                 realEstateListFiltered = filterResults.values as MutableList<RealEstate>
                notifyDataSetChanged()
            }
        }
    }
    fun addRealEtate(realEstate: RealEstate) {
        realEstateListFiltered.add(realEstate)
        realEstateList.add(realEstate)
    }
    fun ownerNameSort() {
        realEstateList.sortWith(Comparator { real1, real2 ->
            real1.owner.compareTo(real2.owner)
        })
    }

    fun squareFootSort() {
        realEstateList.sortWith(Comparator { real1, real2 ->
            (real1.squareFootage - real2.squareFootage).toInt()

        })
    }
}