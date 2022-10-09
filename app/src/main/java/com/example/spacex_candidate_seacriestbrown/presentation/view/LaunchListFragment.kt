package com.example.spacex_candidate_seacriestbrown.presentation.view

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.SharedElementCallback
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
class LaunchListFragment : Fragment() {

    // Backing the binding variable to avoid memory leaks
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

        postponeEnterTransition()
        configureObserver()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setExitTransition()
        setReturnTransition()
    }

    private fun configureObserver() {
        viewModel.launches.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
                binding.apply {
                    pbLoading.visibility = View.GONE
                    tvErrorText.visibility = View.VISIBLE
                    btnRetry.visibility = View.VISIBLE

                    btnRetry.setOnClickListener {
                        pbLoading.visibility = View.VISIBLE
                        tvErrorText.visibility = View.GONE
                        it.visibility = View.GONE
                        viewModel.fetchLaunches()
                    }
                }
            } else {
                binding.apply {
                    pbLoading.visibility = View.GONE
                    tvErrorText.visibility = View.GONE

                    rvLaunchList.apply {
                        adapter = launchListAdapter
                        setHasFixedSize(true)
                        launchListAdapter.submitList(list)
                        viewTreeObserver.addOnPreDrawListener {
                            startPostponedEnterTransition()
                            true
                        }
                    }
                }
            }
        }
    }

    private fun setExitTransition() {
        exitTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.list_exit_transition)
    }

    private fun setReturnTransition() {
        reenterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.list_return_transition)
    }

    private fun viewDetails(iv: ImageView, data: EntityLaunchData) {
        parentFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .addSharedElement(iv, iv.transitionName)
            .replace(R.id.fragment_container, LaunchDetailsFragment.getInstance(data))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}