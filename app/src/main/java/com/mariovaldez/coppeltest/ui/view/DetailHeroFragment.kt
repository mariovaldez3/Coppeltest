package com.mariovaldez.coppeltest.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mariovaldez.coppeltest.R
import com.mariovaldez.coppeltest.data.model.SuperHero
import com.mariovaldez.coppeltest.databinding.FragmentDetailHeroBinding
import com.mariovaldez.coppeltest.other.Const
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHeroFragment : Fragment() {

    lateinit var binding : FragmentDetailHeroBinding

    lateinit var superHero: SuperHero
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            superHero = it.getSerializable(Const.HERO) as SuperHero
            println(superHero)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailHeroBinding.inflate(inflater,container,false)
        start()
        return binding.root
    }

    private fun start() {
        Glide.with(requireContext())
            .load(superHero.image.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .error(R.drawable.ic_people)
            .into(binding.imgDetailHero)
        binding.name.text = superHero.name
        //Biography
        binding.biographyInclude.fullName.text = superHero.biography.`full-name`
        binding.biographyInclude.alterEgos.text = superHero.biography.`alter-egos`
        binding.biographyInclude.placeOfBirth.text = superHero.biography.`place-of-birth`
        binding.biographyInclude.publisher.text = superHero.biography.publisher

        //powerstats
        binding.powerStatsInclude.powerstatIntelligencyQuantity.text = superHero.powerstats.intelligence
        binding.powerStatsInclude.prowerstatCombatQuantity.text = superHero.powerstats.combat
        binding.powerStatsInclude.prowerstatDurabilityQuantity.text = superHero.powerstats.durability
        binding.powerStatsInclude.prowerstatPowerQuantity.text = superHero.powerstats.power
        binding.powerStatsInclude.prowerstatSpeedQuantity.text = superHero.powerstats.speed
        binding.powerStatsInclude.prowerstatStrengthQuantity.text = superHero.powerstats.strength

        //appearance
        binding.appearanceInclude.gender.text = superHero.appearance.gender
        binding.appearanceInclude.race.text = superHero.appearance.race
        binding.appearanceInclude.height.text = superHero.appearance.height[1]
        binding.appearanceInclude.weight.text = superHero.appearance.weight[1]

        //work
        binding.workOccupation.text = superHero.work.occupation
        binding.workBase.text = superHero.work.base

        //connections
        binding.connectionsInclude.affiliationText.text = superHero.connections.`group-affiliation`
        binding.connectionsInclude.relativesText.text = superHero.connections.relatives
    }
}