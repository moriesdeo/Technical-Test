package com.example.axiata.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.axiata.entity.BaseResponse
import com.example.axiata.entity.ResultsItem
import com.example.axiata.repositorymodel.RepositoryMainModel

class VmMovies(application: Application) : ViewModel() {
    private var repositoryMain = RepositoryMainModel(application)

    var liveListMovies = MutableLiveData<BaseResponse<List<ResultsItem>>>()
    internal fun getListMovies() {
        repositoryMain.getListMovies(1, {
            liveListMovies.postValue(it)
        }, {

        })
    }
}