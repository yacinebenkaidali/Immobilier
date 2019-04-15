package com.esi.projetmobile.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import com.esi.projetmobile.Adapter.RealEstateAdapter.ViewHolder
import com.esi.projetmobile.Model.RealEstate
import com.esi.projetmobile.R
import kotlinx.android.synthetic.main.realestate_item.view.*


class RealEstateAdapter(realEstateList: MutableList<RealEstate>, context: Context) :
    RecyclerView.Adapter<ViewHolder>(), Filterable {


    private var realEstateList: MutableList<RealEstate> = realEstateList
    private var realEstateListFiltered: MutableList<RealEstate> = realEstateList
    private var context = context
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.realestate_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return realEstateListFiltered.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val realEstate = realEstateListFiltered[p1]
        p0.ownerName.text = realEstate.owner
        p0.squareFootage.text = realEstate.squareFootage.toString()
        // p0.realEstatewImg.setI=realEstate.owner
        p0.detailButton.setOnClickListener {
            val args = Bundle()
            args.putString("Name", realEstate.owner)
            Navigation.findNavController(it).navigate(R.id.action_ads_to_DetailFragment, args)
        }
        p0.itemCard.setOnClickListener {
            val gmmIntentUri = Uri.parse(realEstate.coordinates)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapIntent)
            }
        }

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemCard = itemView.brand_card!!
        var ownerName = itemView.owner_name!!
        var squareFootage = itemView.square_foot!!
        var realEstatewImg = itemView.realestate_image
        var detailButton = itemView.realestate_but!!
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                realEstateListFiltered = if (charString.isEmpty()) {
                    realEstateList
                } else {
                    val filteredList = mutableListOf<RealEstate>()
                    for (row in realEstateList) {
                        if (row.owner.toLowerCase().contains(charString.toLowerCase()) || row.condition.contains(
                                charSequence
                            )
                        ) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
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