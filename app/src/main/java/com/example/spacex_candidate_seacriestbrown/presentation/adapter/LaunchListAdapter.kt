package com.example.spacex_candidate_seacriestbrown.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.example.spacex_candidate_seacriestbrown.databinding.LaunchCardItemBinding
import com.example.spacex_candidate_seacriestbrown.util.GlideDrawer

class LaunchListAdapter(private val viewDetails: (ImageView, EntityLaunchData) -> Unit)
    : ListAdapter<EntityLaunchData, LaunchListAdapter.LaunchCardViewHolder>(EntityDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchCardViewHolder =
        LaunchCardViewHolder(
            LaunchCardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

    override fun onBindViewHolder(holder: LaunchCardViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class LaunchCardViewHolder(private val binding: LaunchCardItemBinding)
        :  RecyclerView.ViewHolder(binding.root) {
            fun onBind(entityData: EntityLaunchData) {
                binding.apply {
                    tvMissionName.text = entityData.missionName
                    tvLaunchName.text = entityData.launchSiteName
                    tvLaunchDate.text = entityData.launchDate
                    tvRocketName.text = entityData.rocketName

                    ivPatch.apply {
                        transitionName = entityData.patchImage
                        GlideDrawer.drawImage(this, transitionName)
                        setOnClickListener {
                            viewDetails(this, entityData)
                        }
                    }
                }
            }
        }

    private class EntityDiffUtil: DiffUtil.ItemCallback<EntityLaunchData>() {
        override fun areItemsTheSame(
            oldItem: EntityLaunchData,
            newItem: EntityLaunchData
        ): Boolean = oldItem.flightNumber == newItem.flightNumber

        override fun areContentsTheSame(
            oldItem: EntityLaunchData,
            newItem: EntityLaunchData
        ): Boolean = oldItem == newItem

    }
}