package com.example.axiata.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.axiata.entity.BaseResponse
import com.example.axiata.entity.ResultsItem
import com.example.axiata.repositorymodel.RepositoryMainModel

class VmDiscovery(application: Application) : ViewModel() {
    private var repositoryMain = RepositoryMainModel(application)

    var liveDiscoveryMovies = MutableLiveData<BaseResponse<List<ResultsItem>>>()
    internal fun getDiscoveryMovies() {
        repositoryMain.getDiscoveryMovies({
            liveDiscoveryMovies.postValue(it)
        }, {

        })
    }
}