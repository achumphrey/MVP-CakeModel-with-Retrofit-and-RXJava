package com.example.mvpcakemodelwithretrofitandrxjava.presenter

import com.example.mvpcakemodelwithretrofitandrxjava.model.CakeModel


interface ViewInterface {

    fun showProgress()
    fun showError()
    fun onShowList(cakeModel: List<CakeModel>)
}