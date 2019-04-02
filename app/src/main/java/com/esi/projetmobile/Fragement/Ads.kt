package com.esi.projetmobile.Fragement

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esi.projetmobile.Adapter.RealEstateAdapter
import com.esi.projetmobile.Model.RealEstate
import com.esi.projetmobile.R
import kotlinx.android.synthetic.main.fragment_ads.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Ads : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    lateinit var realEstateList: MutableList<RealEstate>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        realEstateList = mutableListOf(
            RealEstate(1, "yacine", "Nice one", 154.02),
            RealEstate(1, "zineddine", "5050", 202.02),
            RealEstate(1, "Ahmed", "Acceptable", 95.02),
            RealEstate(1, "Raouf", "Nice one", 310.02)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }
    fun initRecyclerView() {
        realestatelist.setHasFixedSize(true)
        realestatelist.layoutManager = LinearLayoutManager(context)
        realestatelist.adapter = RealEstateAdapter(realEstateList, context!!)
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

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Ads().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
