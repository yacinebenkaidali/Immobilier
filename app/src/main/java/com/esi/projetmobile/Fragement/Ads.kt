package com.esi.projetmobile.Fragement

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import com.esi.projetmobile.Adapter.RealEstateAdapter
import com.esi.projetmobile.Model.RealEstate
import com.esi.projetmobile.R
import com.esi.projetmobile.Utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.data_entry_dialog.view.*
import kotlinx.android.synthetic.main.fragment_ads.*


val spinnerChoices = arrayOf("Owner", "SquareFootage")

class Ads : Fragment() {


    private var listener: OnFragmentInteractionListener? = null
    private var utils = Utils()
    private lateinit var adapter: RealEstateAdapter
    private  var realEstate= RealEstate()

    lateinit var realEstateList: MutableList<RealEstate>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realEstateList = mutableListOf(
            RealEstate(1, "yacine", "Nice one", 154.02, "geo:36.7538,3.0588"),
            RealEstate(1, "zineddine", "5050", 202.02, "geo:37.7749,-122.4194"),
            RealEstate(1, "Ahmed", "Acceptable", 95.02, "geo:37.7749,-122.4194"),
            RealEstate(1, "Raouf", "Nice one", 310.02, "geo:37.7749,-122.4194")
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
        adapter = RealEstateAdapter(realEstateList, context!!)
        initRecyclerView()
        addrealestate.setOnClickListener {
            val mBuilder = AlertDialog.Builder(context!!)
            val mView = layoutInflater.inflate(R.layout.data_entry_dialog, null)
            mBuilder.setView(mView)
            val dialog = mBuilder.create()
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
            mView.btnImg.setOnClickListener {
                //utils.openImageChooser(dialog, context!!)
                val intent = Intent()
                    .setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                    .setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(Intent.createChooser(intent, "Select a file"), 301)
            }
            mView.btnOk.setOnClickListener {
                addItem(mView)
                Log.i("insert", "Okay")
                dialog.dismiss()
            }
        }

        activity!!.filterList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                adapter.filter.filter(query)
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        spinner_sort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        adapter.ownerNameSort()
                    }
                    else -> {
                        adapter.squareFootSort()
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun initRecyclerView() {
        realestatelist.setHasFixedSize(true)
        realestatelist.layoutManager = LinearLayoutManager(context)
        realestatelist.adapter = adapter
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("nice", "cool")
        if ((requestCode == 301) && (resultCode == Activity.RESULT_OK)) {
            if (data!!.data != null) {
                realEstate.images.add(data.data!!)
            } else if (data.clipData != null) {
                var clipArray = data.clipData
                for (i in 0 until clipArray!!.itemCount) {
                    realEstate.images.add(clipArray.getItemAt(i).uri)
                }
                Log.i("size", realEstate.images.size.toString())
            }
        }
    }

    private fun addItem(mView: View) {
        realEstate = RealEstate(
            1,
            mView.Owner.text.toString(),
            mView.Cond.text.toString(),
            mView.SquareFoot.text.toString().toDouble(),
            "geo:37.7749,-122.4194"
        )
        realEstateList.add(realEstate)
        realestatelist.adapter!!.notifyDataSetChanged()
    }

}
