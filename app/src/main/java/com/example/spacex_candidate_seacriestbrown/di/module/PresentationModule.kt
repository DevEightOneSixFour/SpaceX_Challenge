package com.example.spacex_candidate_seacriestbrown.di.module

import com.example.spacex_candidate_seacriestbrown.domain.repository.LaunchLocalRepository
import com.example.spacex_candidate_seacriestbrown.domain.repository.LaunchRemoteRepository
import com.example.spacex_candidate_seacriestbrown.domain.usecase.LaunchApiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    @Provides
    fun provideLaunchUseCase(
        remoteRepo: LaunchRemoteRepository,
        localRepo: LaunchLocalRepository
    ): LaunchApiUseCase =
        LaunchApiUseCase(remoteRepo, localRepo)
}