package com.example.axiata.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.axiata.R
import com.example.axiata.adapter.AdapterDiscoveryMovies
import com.example.axiata.di.Injection
import com.example.axiata.entity.ResultsItem
import com.example.axiata.viewmodel.VmMovies
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            Injection.provideViewModelFactory(requireActivity().application)
        ).get(VmMovies::class.java)
    }
    private lateinit var adapterDiscoveryMovies: AdapterDiscoveryMovies

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        liveData()
    }

    private fun liveData() {
        viewModel.liveListMovies.observe(viewLifecycleOwner, Observer {
            progMovies.visibility = View.GONE
            adapterDiscoveryMovies.addList(it.results as ArrayList<ResultsItem>)
            rvMovies.adapter = adapterDiscoveryMovies
        })
    }

    private fun initView() {
        adapterDiscoveryMovies = AdapterDiscoveryMovies()
        rvMovies.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onResume() {
        super.onResume()
        adapterDiscoveryMovies.clearList()
        viewModel.getListMovies()
    }
}
