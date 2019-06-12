package com.esi.projetmobile.adapter

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.navigation.Navigation
import com.esi.projetmobile.R
import com.esi.projetmobile.adapter.RealEstateAdapter.ViewHolder
import com.esi.projetmobile.fragment.AdsFragementDirections
import com.esi.projetmobile.model.RealEstate
import kotlinx.android.synthetic.main.realestate_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Comparator


class RealEstateAdapter(private var realEstateList: MutableList<RealEstate>, private var context: Context) :
    RecyclerView.Adapter<ViewHolder>(), Filterable {
    private var realEstateListFiltered: MutableList<RealEstate> = realEstateList
    private val CALL_REQUEST = 100

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.realestate_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return realEstateListFiltered.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val realEstate = realEstateListFiltered[p1]
        val date = Date(realEstate.date)
        val fmt = SimpleDateFormat("yyyy-MM-dd")

        p0.ownerName.text = realEstate.owner
        p0.squareFootage.text = realEstate.squareFootage.toString()
        val dateString = fmt.format(date)
        p0.dateDis.text = dateString
        p0.cityName.text = context.getString(R.string.city, realEstate.wilaya)
        if (realEstate.images.size != 0) {
            p0.realEstatewImg.setImageURI(Uri.parse(realEstate.images[0]))
        } else {
            p0.realEstatewImg.setImageResource(R.drawable.skyscraper)
        }
        p0.detailButton.setOnClickListener {
            Navigation.findNavController(it).navigate(AdsFragementDirections.actionAdsToDetailFragment(realEstate))
        }
        p0.itemCard.setOnClickListener {
            displayDialog(realEstate)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemCard = itemView.brand_card!!
        var ownerName = itemView.owner_name!!
        var cityName = itemView.city_name!!
        var dateDis = itemView.date_dis!!
        var squareFootage = itemView.square_foot!!
        var realEstatewImg = itemView.realestate_image!!
        var detailButton = itemView.realestate_but!!
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

    fun dateSort() {
        realEstateList.sortWith(Comparator { real1, real2 ->
            val date1 = Date(real1.date)
            val date2 = Date(real2.date)
            date1.compareTo(date2)
        })
    }


    private fun displayDialog(realEstate: RealEstate) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Choose your action ?")
        dialogBuilder.setMessage("Which action u want to go to ?!")
            .setCancelable(true)
            .setPositiveButton("Google Maps") { dialog, id ->
                val gmmIntentUri = Uri.parse(realEstate.coordinates)
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                if (mapIntent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(mapIntent)
                }
            }
            .setNegativeButton("Call his/her Phone") { dialog, id ->
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:" + realEstate.phone)
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        context as Activity
                        , arrayOf(Manifest.permission.CALL_PHONE), CALL_REQUEST
                    )
                    Toast.makeText(context, "grant me permissions", Toast.LENGTH_LONG).show()
                } else {
                    context.startActivity(callIntent)
                }

            }
        val alert = dialogBuilder.create()
        alert.setCanceledOnTouchOutside(true)
        alert.setTitle("Immobilier App")
        alert.show()
    }


}