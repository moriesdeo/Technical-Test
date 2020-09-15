package com.example.axiata.repositorymodel

import android.app.Application
import com.example.axiata.BuildConfig
import com.example.axiata.entity.BaseResponse
import com.example.axiata.entity.ResultsItem
import com.example.axiata.network.RestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryMainModel(application: Application) {
    private val service = RestApi.create()

    fun getDiscoveryMovies(
        onResult: (BaseResponse<List<ResultsItem>>?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getDiscoveryMovies(BuildConfig.API_KEY, "en-US")
            .enqueue(object : Callback<BaseResponse<List<ResultsItem>>> {
                override fun onFailure(call: Call<BaseResponse<List<ResultsItem>>>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<BaseResponse<List<ResultsItem>>>,
                    response: Response<BaseResponse<List<ResultsItem>>>
                ) {
                    if (response.isSuccessful) onResult(response.body())
                }

            })
    }

    fun getListMovies(
        page: Int,
        onResult: (BaseResponse<List<ResultsItem>>?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getListMovies(BuildConfig.API_KEY, "en-US", page)
            .enqueue(object : Callback<BaseResponse<List<ResultsItem>>> {
                override fun onFailure(call: Call<BaseResponse<List<ResultsItem>>>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<BaseResponse<List<ResultsItem>>>,
                    response: Response<BaseResponse<List<ResultsItem>>>
                ) {
                    if (response.isSuccessful) onResult(response.body())
                }

            })
    }
}