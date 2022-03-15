package com.mories.test.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mories.test.R
import com.mories.test.adapter.AdapterDiscoveryMovies
import com.mories.test.databinding.FragmentMoviesBinding
import com.mories.test.di.Injection
import com.mories.test.entity.ResultsItem
import com.mories.test.viewmodel.VmMovies

class MoviesFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            Injection.provideViewModelFactory(requireActivity().application)
        ).get(VmMovies::class.java)
    }
    private lateinit var adapterDiscoveryMovies: AdapterDiscoveryMovies
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        liveData()
    }

    private fun liveData() {
        viewModel.liveListMovies.observe(viewLifecycleOwner, Observer {
            binding.progMovies.visibility = View.GONE
            adapterDiscoveryMovies.addList(it.results as ArrayList<ResultsItem>)
            binding.rvMovies.adapter = adapterDiscoveryMovies
        })
    }

    private fun initView() {
        adapterDiscoveryMovies = AdapterDiscoveryMovies()
        binding.rvMovies.apply {
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
