package com.example.spacex_candidate_seacriestbrown.presentation.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.spacex_candidate_seacriestbrown.R
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.example.spacex_candidate_seacriestbrown.databinding.FragmentLaunchDetailsBinding

class LaunchDetailsFragment: Fragment() {

    private var _binding: FragmentLaunchDetailsBinding? = null
    private val binding: FragmentLaunchDetailsBinding get() = _binding!!

    private lateinit var entityLaunchData: EntityLaunchData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSharedElementTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLaunchDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()

        entityLaunchData = arguments?.getParcelable(KEY)!!

        binding.ivDetailPatch.apply {
            transitionName = entityLaunchData.patchImage
            handleEnterTransitionAfterLoading(transitionName, this)
        }
    }

    private fun handleEnterTransitionAfterLoading(imageAddress: String, imageView: ImageView) {
        Glide.with(this)
            .load(imageAddress)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            }).into(imageView)
    }

    private fun setSharedElementTransition() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_element_transition)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val KEY = "KEY"

        fun getInstance(data: EntityLaunchData): LaunchDetailsFragment {
            val fragment = LaunchDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY, data)
            fragment.arguments = bundle

            return fragment
        }
    }
}