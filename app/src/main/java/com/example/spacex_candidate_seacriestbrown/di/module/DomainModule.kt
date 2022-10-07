package com.example.spacex_candidate_seacriestbrown.di.module

import com.example.spacex_candidate_seacriestbrown.data.api.ApiService
import com.example.spacex_candidate_seacriestbrown.data.room.LaunchDatabase
import com.example.spacex_candidate_seacriestbrown.domain.repository.LaunchLocalRepository
import com.example.spacex_candidate_seacriestbrown.domain.repository.LaunchLocalRepositoryImpl
import com.example.spacex_candidate_seacriestbrown.domain.repository.LaunchRemoteRepository
import com.example.spacex_candidate_seacriestbrown.domain.repository.LaunchRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideLaunchRemoteRepository(
        api: ApiService
    ): LaunchRemoteRepository =
        LaunchRemoteRepositoryImpl(api)

    @Provides
    fun provideLaunchLocalRepository(
        db: LaunchDatabase
    ): LaunchLocalRepository =
        LaunchLocalRepositoryImpl(db)
}