package com.esi.projetmobile.fragment

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esi.projetmobile.R
import com.esi.projetmobile.model.RealEstate
import kotlinx.android.synthetic.main.details_fragement.view.*


class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragement, container, false)
        val recievedRealEstate = arguments!!.getParcelable<RealEstate>("Parcelable")!!
//        view.users_name.text = arguments!!.getString("Name")
        if (recievedRealEstate.images.isEmpty()) {
            view.users_img.setImageResource(R.drawable.skyscraper)
        } else {
            view.users_img.setImageURI(Uri.parse(recievedRealEstate.images[0]))
        }

        view.users_name.text = recievedRealEstate.owner
        //Phone number
        return view
    }

}
