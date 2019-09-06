package com.example.mvpcakemodelwithretrofitandrxjava.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpcakemodelwithretrofitandrxjava.PresenterImp

import com.example.mvpcakemodelwithretrofitandrxjava.R
import com.example.mvpcakemodelwithretrofitandrxjava.model.CakeModel
import com.example.mvpcakemodelwithretrofitandrxjava.presenter.ViewInterface
import kotlinx.android.synthetic.main.fragment_cake_model.*

class CakeModelFragment : Fragment(), ViewInterface {

    private lateinit var presenterImp: PresenterImp

    override fun showProgress() {
        prgs_bar.visibility = View.VISIBLE
    }

    override fun showError() {
        error_include.visibility = View.VISIBLE
        prgs_bar.visibility = View.GONE
    }

    override fun onShowList(cakeModel: List<CakeModel>) {
        prgs_bar.visibility = View.GONE
        error_include.visibility = View.GONE


        val adapter: CakeAdapter = CakeAdapter(cakeModel!!)
        rv_list.layoutManager = LinearLayoutManager(activity)
        rv_list.adapter = adapter
    }

    override fun onDestroy() { // this is the activity's onDestroy() method call
        super.onDestroy()
        presenterImp.onDestroy()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cake_model, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenterImp = PresenterImp(this)
        showProgress()
    }

    override fun onResume() {
        super.onResume()
        presenterImp.processCall()
    }



}
