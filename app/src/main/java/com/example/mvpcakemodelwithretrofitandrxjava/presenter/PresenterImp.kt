package com.example.mvpcakemodelwithretrofitandrxjava

import android.util.Log
import com.example.mvpcakemodelwithretrofitandrxjava.model.CakeModel
import com.example.mvpcakemodelwithretrofitandrxjava.network.ClientInterface
import com.example.mvpcakemodelwithretrofitandrxjava.network.RetrofitInstance
import com.example.mvpcakemodelwithretrofitandrxjava.presenter.PresenterInterface
import com.example.mvpcakemodelwithretrofitandrxjava.presenter.ViewInterface
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PresenterImp(_view: ViewInterface): PresenterInterface {

    var view: ViewInterface? = _view
    var compositeDisposable = CompositeDisposable()
    lateinit var disposable: Disposable

    override fun processCall() {

        val cakeModelInterface =
            RetrofitInstance().retrofitInstance.create(ClientInterface::class.java)

        val cakeModelObservable: Observable<List<CakeModel>>
                = cakeModelInterface.getCakeRecords()

        cakeModelObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
       //     .subscribe(starWarsObserver())
            .subscribe({t-> view?.onShowList(t)}, { view?.showError()})
    }

    private fun cakeObserver(): Observer<List<CakeModel>>{

        return object : Observer<List<CakeModel>>{

            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<CakeModel>) {
                Log.d("Presenter", t[0].title)
                view?.onShowList(t)
            }

            override fun onError(e: Throwable) {
            }
        }
    }

    override fun onDestroy() {
        view = null
        disposable.dispose()
        compositeDisposable.clear()
    }
}