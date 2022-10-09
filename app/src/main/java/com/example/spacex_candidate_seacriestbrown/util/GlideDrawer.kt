package com.example.spacex_candidate_seacriestbrown.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.spacex_candidate_seacriestbrown.R

object GlideDrawer {
    fun drawImage(iv: ImageView, link: String?){
        Glide.with(iv)
            .load(link)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.spacex_logo2)
            .into(iv)
    }
}