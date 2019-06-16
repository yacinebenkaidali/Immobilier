package com.esi.projetmobile.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import com.esi.projetmobile.adapter.RealEstateAdapter
import com.esi.projetmobile.model.RealEstate
import com.esi.projetmobile.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.data_entry_dialog.view.*
import kotlinx.android.synthetic.main.fragment_ads.*
import java.util.*
import kotlin.collections.ArrayList


class AdsFragement : androidx.fragment.app.Fragment() {
    private lateinit var adapter: RealEstateAdapter
    private var uriList = mutableListOf<String>()

    private  var realEstateList= mutableListOf<RealEstate>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realEstateList = mutableListOf(
            RealEstate(
                1,
                "yacine",
                "Alger",
                154.02,
                "geo:36.7538,3.0588",
                "Maison",
                "0239",
                Date().time,
                mutableListOf()
            ),
            RealEstate(
                1,
                "zineddine",
                "Oran",
                202.02,
                "geo:37.7749,-122.4194",
                "Maison",
                "23729",
                Date().time,
                mutableListOf()
            ),
            RealEstate(
                1,
                "Ahmed",
                "Costantine",
                95.02,
                "geo:37.7749,-122.4194",
                "Maison",
                "238642",
                Date().time,
                mutableListOf()
            ),
            RealEstate(
                1,
                "Raouf",
                "Annaba",
                310.02,
                "geo:37.7749,-122.4194",
                "Maison",
                "3294",
                Date().time,
                mutableListOf()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedInstanceState?.let {
            realEstateList= it.getParcelableArrayList("listObjects")!!
        }
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
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
            mView.btnImg.setOnClickListener {
                val intent = Intent()
                    .setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                    .setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(Intent.createChooser(intent, "Select a file"), 301)
            }
            mView.btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            mView.btnOk.setOnClickListener {
                addItem(mView)
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
        spinner_sort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        adapter.wilayaNameSort()
                    }
                    1 -> {
                        adapter.squareFootSort()
                    }
                    else -> {
                        adapter.dateSort()
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }
    private fun initRecyclerView() {
        realestatelist.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val list =realEstateList.toList()
        outState.putParcelableArrayList("listObjects",list as java.util.ArrayList<out Parcelable>)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == 301) && (resultCode == Activity.RESULT_OK)) {
            if (data!!.data != null) {
                uriList.add(data.data!!.toString())
            } else if (data.clipData != null) {
                val clipArray = data.clipData
                for (i in 0 until clipArray!!.itemCount) {
                    uriList.add(clipArray.getItemAt(i).uri.toString())
                }
            }
        }
    }

    private fun addItem(mView: View) {
        val realEstate = RealEstate(
            1,
            mView.Owner.text.toString(),
            mView.Cond.text.toString(),
            mView.SquareFoot.text.toString().toDouble(),
            "geo:37.7749,-122.4194",
            mView.type.selectedItem.toString(),
            mView.phone.text.toString(),
            Date().time,
            mutableListOf()
        )
        realEstate.images.addAll(uriList)
        realEstateList.add(realEstate)
        realestatelist.adapter!!.notifyDataSetChanged()
        uriList.clear()
    }
}
