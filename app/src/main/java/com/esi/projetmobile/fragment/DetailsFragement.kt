package com.esi.projetmobile.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.esi.projetmobile.R
import com.esi.projetmobile.adapter.SliderAdapter
import com.esi.projetmobile.model.RealEstate
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.details_fragement.view.*

class DetailsFragment : Fragment() {
    companion object {
        const val CALL_REQUEST = 100
    }

    private var recievedRealEstate: RealEstate? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragement, container, false)
        recievedRealEstate = savedInstanceState?.getParcelable("Object")
        if (recievedRealEstate == null) {
            recievedRealEstate = DetailsFragmentArgs.fromBundle(arguments!!).estate //type safe argument
        }

        arguments!!.clear()
        view.users_phone.text = recievedRealEstate?.phone
        view.users_name.text = recievedRealEstate?.owner
        view.user_wilaya.text = recievedRealEstate?.wilaya

        view.user_call.setOnClickListener {
            callIntent(recievedRealEstate!!)
        }
        view.user_goto.setOnClickListener {
            mapIntent(recievedRealEstate!!)
        }

        view.imageSlider.sliderAdapter = SliderAdapter(context!!, recievedRealEstate!!.images)
        view.imageSlider.startAutoCycle()
        view.imageSlider.setIndicatorAnimation(IndicatorAnimations.SWAP)
        view.imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        view.imageSlider.scrollTimeInSec = 5
        return view
    }

    private fun callIntent(recievedRealEstate: RealEstate) {
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:" + recievedRealEstate.phone)
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity
                , arrayOf(Manifest.permission.CALL_PHONE), CALL_REQUEST
            )
            Toast.makeText(context, "grant me permissions", Toast.LENGTH_LONG).show()
        } else {
            context?.startActivity(callIntent)
        }
    }

    private fun mapIntent(recievedRealEstate: RealEstate) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=${recievedRealEstate.wilaya}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(context?.packageManager!!) != null) {
            context?.startActivity(mapIntent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("Object", recievedRealEstate)
    }
}
