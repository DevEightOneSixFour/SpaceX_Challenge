package com.example.spacex_candidate_seacriestbrown.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.spacex_candidate_seacriestbrown.R

object GlideDrawer {
    fun drawImage(iv: ImageView, link: String?){
        Glide.with(iv)
            .load(link)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.downloading_placeholder)
            .fallback(R.drawable.spacex_logo)
            .into(iv)
    }
}