package lht.com.hometest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import lht.com.hometest.api.ApiClient

class MainViewModel: ViewModel(){
    private var disposables: CompositeDisposable = CompositeDisposable()
    private var keywordsObs: MutableLiveData<ArrayList<String>> = MutableLiveData()

    /**
     * Fetch keyword list
     */
    fun loadKeywords(){
        ApiClient.getInstance().getKeywordAPI()
            .getKeywords()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<String>>{
                override fun onSuccess(t: ArrayList<String>) {
                    keywordsObs.postValue(t)
                }

                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onError(e: Throwable) {
                    keywordsObs.postValue(null)
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    /**
     * @return Object for observe keyword value change
     */
    fun getKeywordObs(): LiveData<ArrayList<String>>{
        return keywordsObs
    }
}