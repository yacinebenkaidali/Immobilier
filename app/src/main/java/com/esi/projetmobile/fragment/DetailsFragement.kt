package com.esi.projetmobile.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esi.projetmobile.R
import com.esi.projetmobile.adapter.RealEstateGridAdapter
import com.esi.projetmobile.model.RealEstate
import kotlinx.android.synthetic.main.details_fragement.view.*
class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragement, container, false)
        val recievedRealEstate = arguments!!.getParcelable<RealEstate>("Parcelable")!!
        if (recievedRealEstate.images.isEmpty()) {
            view.grid_list.adapter=RealEstateGridAdapter(mutableListOf())
        } else {
            view.grid_list.adapter=RealEstateGridAdapter(recievedRealEstate.images)
        }
        view.users_phone.append("he lives in ${recievedRealEstate.coordinates}")
        view.users_name.append("his name is ${recievedRealEstate.owner}")
        return view
    }
}
