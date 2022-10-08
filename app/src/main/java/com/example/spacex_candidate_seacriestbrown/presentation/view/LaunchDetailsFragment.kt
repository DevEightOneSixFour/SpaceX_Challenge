package com.example.spacex_candidate_seacriestbrown.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.example.spacex_candidate_seacriestbrown.databinding.FragmentLaunchDetailsBinding

class LaunchDetailsFragment: Fragment() {

    companion object {
        const val KEY = "KEY"

        fun getInstance(data: EntityLaunchData): LaunchDetailsFragment {
            val fragment = LaunchDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY, data)
            fragment.arguments = bundle

            return fragment
        }
    }

    private var _binding: FragmentLaunchDetailsBinding? = null
    private val binding: FragmentLaunchDetailsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLaunchDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}