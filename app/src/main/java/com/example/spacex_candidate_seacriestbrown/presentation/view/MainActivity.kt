package com.example.spacex_candidate_seacriestbrown.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.spacex_candidate_seacriestbrown.R
import com.example.spacex_candidate_seacriestbrown.databinding.ActivityMainBinding
import com.example.spacex_candidate_seacriestbrown.presentation.viewmodel.SpaceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SpaceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Network call only happens when first opening the app
        if (viewModel.launches.value.isNullOrEmpty()) {
            viewModel.fetchLaunches()
        }
    }
}