package com.example.spacex_candidate_seacriestbrown.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.spacex_candidate_seacriestbrown.R
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.example.spacex_candidate_seacriestbrown.databinding.LaunchCardItemBinding
import com.example.spacex_candidate_seacriestbrown.util.GlideDrawer

class LaunchListAdapter(private val viewDetails: (EntityLaunchData) -> Unit)
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
                        GlideDrawer.drawImage(this, entityData.patchImage)
                        setOnClickListener {
                            viewDetails(entityData)
                        }
                    }
                }
            }
        }

    private class EntityDiffUtil: DiffUtil.ItemCallback<EntityLaunchData>() {
        override fun areItemsTheSame(
            oldItem: EntityLaunchData,
            newItem: EntityLaunchData
        ): Boolean = oldItem.missionId == newItem.missionId

        override fun areContentsTheSame(
            oldItem: EntityLaunchData,
            newItem: EntityLaunchData
        ): Boolean = oldItem == newItem

    }
}