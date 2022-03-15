package com.mories.test.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mories.test.R
import com.mories.test.adapter.AdapterDiscoveryMovies
import com.mories.test.databinding.FragmentDiscoveryBinding
import com.mories.test.di.Injection
import com.mories.test.entity.ResultsItem
import com.mories.test.viewmodel.VmDiscovery

class DiscoveryFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            Injection.provideViewModelFactory(requireActivity().application)
        ).get(VmDiscovery::class.java)
    }
    private lateinit var adapterDiscoveryMovies: AdapterDiscoveryMovies
    private lateinit var binding: FragmentDiscoveryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discovery, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        liveData()
    }

    override fun onResume() {
        super.onResume()
        adapterDiscoveryMovies.clearList()
        viewModel.getDiscoveryMovies()
    }

    private fun liveData() {
        val mRestID = R.anim.layout_animation_fall_down
        val layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, mRestID)
        viewModel.liveDiscoveryMovies.observe(viewLifecycleOwner, Observer {
            binding.progDiscovery.visibility = View.GONE
            if (it.results != null) {
                adapterDiscoveryMovies.addList(it.results as ArrayList<ResultsItem>)
                binding.rvDicovery.adapter = adapterDiscoveryMovies
                binding.rvDicovery.layoutAnimation = layoutAnimationController
            }
        })
    }

    private fun initView() {
        adapterDiscoveryMovies = AdapterDiscoveryMovies()
        binding.rvDicovery.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
    }
}
