package com.esi.projetmobile.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esi.projetmobile.R
import com.esi.projetmobile.adapter.SliderAdapter
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
        val recievedRealEstate = DetailsFragmentArgs.fromBundle(arguments!!).estate //type safe argument
        Log.i("adapter",recievedRealEstate.owner +recievedRealEstate.phone +recievedRealEstate.type)
        view.users_phone.text=recievedRealEstate.phone
        view.users_name.text = recievedRealEstate.owner
        view.user_type.text = recievedRealEstate.type

        view.imageSlider.sliderAdapter = SliderAdapter(context!!, recievedRealEstate.images)
        view.imageSlider.startAutoCycle()
        view.imageSlider.setIndicatorAnimation(IndicatorAnimations.SWAP)
        view.imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        view.imageSlider.scrollTimeInSec = 5
        return view
    }

}
