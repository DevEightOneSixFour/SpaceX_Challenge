package com.example.spacex_candidate_seacriestbrown.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.spacex_candidate_seacriestbrown.R
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.example.spacex_candidate_seacriestbrown.databinding.FragmentLaunchListBinding
import com.example.spacex_candidate_seacriestbrown.presentation.adapter.LaunchListAdapter
import com.example.spacex_candidate_seacriestbrown.presentation.viewmodel.SpaceViewModel
import com.example.spacex_candidate_seacriestbrown.util.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchListFragment: Fragment() {

    private var _binding: FragmentLaunchListBinding? = null
    private val binding: FragmentLaunchListBinding get() = _binding!!

    private val launchListAdapter by lazy { LaunchListAdapter(::viewDetails) }

    private val viewModel: SpaceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLaunchListBinding.inflate(layoutInflater)

        configureObserver()

        return binding.root
    }

    private fun configureObserver() {
        viewModel.launches.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
                // Todo error handle
                Toast.makeText(context, "Failed to load data", Toast.LENGTH_LONG).show()
            } else {
                binding.apply {
                    pbLoading.visibility = View.GONE
                    rvLaunchList.adapter = launchListAdapter
                    launchListAdapter.submitList(list)
                }
            }
        }
    }

    fun viewDetails(data: EntityLaunchData) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LaunchDetailsFragment.getInstance(data))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}