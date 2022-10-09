package com.example.spacex_candidate_seacriestbrown.presentation.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.spacex_candidate_seacriestbrown.R
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.example.spacex_candidate_seacriestbrown.databinding.FragmentLaunchDetailsBinding
import com.example.spacex_candidate_seacriestbrown.presentation.adapter.DetailFlickrAdapter

class LaunchDetailsFragment : Fragment() {

    private var _binding: FragmentLaunchDetailsBinding? = null
    private val binding: FragmentLaunchDetailsBinding get() = _binding!!

    private lateinit var entityLaunchData: EntityLaunchData
    private val flickrAdapter by lazy { DetailFlickrAdapter(moveToPager = ::moveToPager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSharedElementTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()

        entityLaunchData = arguments?.getParcelable(KEY)!!

        renderPage()
    }

    private fun renderPage() {
        binding.apply {
            ivDetailPatch.apply {
                transitionName = entityLaunchData.patchImage
                handleEnterTransitionAfterLoading(transitionName, this)
            }

            tvDetailMissionName.text = Html.fromHtml(
                resources.getString(
                    R.string.details_mission_name,
                    entityLaunchData.missionName
                ), 0
            )
            tvDetailRocket.text = Html.fromHtml(
                resources.getString(
                    R.string.details_rocket_name,
                    entityLaunchData.rocketName
                ), 0
            )
            tvDetailSite.text = Html.fromHtml(
                resources.getString(
                    R.string.details_launch_site,
                    entityLaunchData.launchSiteName
                ), 0
            )
            tvDetailDate.text = Html.fromHtml(
                resources.getString(
                    R.string.details_launch_date,
                    entityLaunchData.launchDate
                ), 0
            )
            tvDetailArticle.text = Html.fromHtml(
                resources.getString(
                    R.string.details_article_link,
                    entityLaunchData.articleLink
                ), 0
            )
            tvDetailVideo.text = Html.fromHtml(
                resources.getString(
                    R.string.details_video_link,
                    entityLaunchData.videoLink
                ), 0
            )

            rvFlickrImages.apply {
                if (!entityLaunchData.flickrImages.isNullOrEmpty()) {
                    adapter = flickrAdapter
                    tvFlickrTitle.text = Html.fromHtml(
                        resources.getString(
                            R.string.details_flickr_title
                        ), 0
                    )
                    flickrAdapter.updateList(entityLaunchData.flickrImages!!)
                    tvFlickrTitle.visibility = View.VISIBLE
                    rvFlickrImages.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun moveToPager(selectedImage: ImageView) {
        Toast.makeText(context, "ViewPager of images here", Toast.LENGTH_SHORT).show()
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