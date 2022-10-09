package com.example.spacex_candidate_seacriestbrown.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex_candidate_seacriestbrown.databinding.DetailFlickrItemBinding
import com.example.spacex_candidate_seacriestbrown.util.GlideDrawer

class DetailFlickrAdapter(
    private val imageList: MutableList<String> = mutableListOf(),
    private val moveToPager: (ImageView) -> Unit
) : RecyclerView.Adapter<DetailFlickrAdapter.FlickrViewHolder>() {

    fun updateList(newList: List<String>) {
        imageList.clear()
        imageList.addAll(newList)
        notifyItemRangeChanged(0, itemCount)
    }

    inner class FlickrViewHolder(private val binding: DetailFlickrItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(link: String) {
            binding.ivFlickrImage.apply {
                GlideDrawer.drawImage(this, link)
                setOnClickListener { moveToPager(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrViewHolder =
        FlickrViewHolder(
            DetailFlickrItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FlickrViewHolder, position: Int) {
        holder.onBind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

}