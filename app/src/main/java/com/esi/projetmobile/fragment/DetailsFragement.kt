package com.esi.projetmobile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esi.projetmobile.R
import com.esi.projetmobile.adapter.RealEstateGridAdapter
import com.esi.projetmobile.adapter.SliderAdapterExample
import com.esi.projetmobile.model.RealEstate
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.details_fragement.*
import kotlinx.android.synthetic.main.details_fragement.view.*
class DetailsFragment : androidx.fragment.app.Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragement, container, false)
        val recievedRealEstate = DetailsFragmentArgs.fromBundle(arguments!!).estate
        if (recievedRealEstate.images.isEmpty()) {
            view.grid_list.adapter=RealEstateGridAdapter(mutableListOf())
        } else {
            view.grid_list.adapter=RealEstateGridAdapter(recievedRealEstate.images)
        }
        view.users_phone.append("he lives in ${recievedRealEstate.phone}")
        view.users_name.append("his phone number is ${recievedRealEstate.owner}")

        view.imageSlider.sliderAdapter = SliderAdapterExample(context!!,recievedRealEstate.images)
        view.imageSlider.startAutoCycle()
        view.imageSlider.setIndicatorAnimation(IndicatorAnimations.SWAP)
        view.imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        view.imageSlider.scrollTimeInSec = 2
        view.imageSlider.startAutoCycle()
        return view
    }
}
