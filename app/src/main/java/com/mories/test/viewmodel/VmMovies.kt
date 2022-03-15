package com.mories.test.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mories.test.entity.BaseResponse
import com.mories.test.entity.ResultsItem
import com.mories.test.repositorymodel.RepositoryMainModel

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