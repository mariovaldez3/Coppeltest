package com.mariovaldez.coppeltest.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mariovaldez.coppeltest.R
import com.mariovaldez.coppeltest.data.model.SuperHero
import com.mariovaldez.coppeltest.databinding.ItemHeroBinding
import com.mariovaldez.coppeltest.other.Const

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchAdapterHolder>(){
    inner class SearchAdapterHolder(val binding: ItemHeroBinding): RecyclerView.ViewHolder(binding.root)
    private val diffCallback = object  : DiffUtil.ItemCallback<SuperHero>(){
        override fun areItemsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this,diffCallback)
    lateinit var context: Context
    var heroItems: List<SuperHero>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterHolder {
        context = parent.context
        val view = ItemHeroBinding.inflate(LayoutInflater.from(context),parent,false)
        return SearchAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapterHolder, position: Int) {
        val currentHero = heroItems[position]
        holder.binding.apply {
            txtNameHero.text = currentHero.name
            Glide.with(context)
                .load(currentHero.image.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .error(R.drawable.ic_people)
                .into(imgHero)

            cardContainer.setOnClickListener { v ->
                val bundle = Bundle()
                bundle.putSerializable(Const.HERO, currentHero)
                if (v != null) {
                    Navigation.findNavController(v)
                        .navigate(R.id.action_nav_fr_search_fragment_to_nav_fr_detail_hero_fragment,
                            bundle)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return heroItems.size
    }
}