package com.esi.projetmobile.Fragement

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esi.projetmobile.Model.RealEstate
import com.esi.projetmobile.R
import kotlinx.android.synthetic.main.details_fragement.view.*


class DetailsFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragement, container, false)
        val recievedRealEstate = arguments!!.getParcelable<RealEstate>("Parcelable")!!
//        view.users_name.text = arguments!!.getString("Name")
        view.users_img.setImageURI(Uri.parse(recievedRealEstate.images[0]))
        view.users_name.text = recievedRealEstate.owner
        //Phone number
        return view
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}
