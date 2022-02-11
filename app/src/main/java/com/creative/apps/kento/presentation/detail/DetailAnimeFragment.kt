package com.creative.apps.kento.presentation.detail

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.creative.apps.kento.R
import com.creative.apps.kento.databinding.FragmentDetailAnimeBinding
import com.creative.apps.kento.domain.local.ServiceError
import com.creative.apps.kento.domain.remote.anime.Anime
import com.creative.apps.kento.domain.remote.characters.Character
import com.creative.apps.kento.framework.viewmodels.AnimeCharactersViewModel
import com.creative.apps.kento.framework.viewmodels.AnimeInformationViewModel
import com.creative.apps.kento.framework.viewmodels.contracts.AnimeCharactersContract
import com.creative.apps.kento.framework.viewmodels.contracts.AnimeInformationContract
import com.creative.apps.kento.presentation.adapters.CharactersRecyclerAdapter
import com.creative.apps.kento.presentation.adapters.listeners.OnCharacterSelectedListener
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailAnimeFragment : Fragment(), AnimeInformationContract.AnimeInformationInterfaceView,
    AnimeCharactersContract.AnimeCharactersInterfaceView, OnCharacterSelectedListener {

    @Inject lateinit var mInformationViewModel : AnimeInformationViewModel
    @Inject lateinit var mCharactersViewModel : AnimeCharactersViewModel
    @Inject lateinit var mCharacterAdapter : CharactersRecyclerAdapter

    private lateinit var mBinding : FragmentDetailAnimeBinding
    private lateinit var mContext : Context
    private var mImageUrl : String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mContext = requireContext()
        mBinding = FragmentDetailAnimeBinding.inflate(LayoutInflater.from(mContext), container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        val id = arguments?.getInt(ANIME_ID_KEY, 0)
        setupRecyclerView()
        setupInformationViewModel(id!!)
        setupCharacterViewModel()
    }

    private fun setupInformationViewModel(id: Int) {
        mInformationViewModel.animeInformation.observe(viewLifecycleOwner) {
            setInformation(it)
        }
        mInformationViewModel.error.observe(viewLifecycleOwner) { error -> onError(error) }
        showProgress()
        mInformationViewModel.getAnimeInformation(id)
    }

    private fun setupCharacterViewModel() {
        mCharactersViewModel.characterList.observe(viewLifecycleOwner){
            populateRecyclerView(it)
        }
        mCharactersViewModel.error.observe(viewLifecycleOwner) { error -> onError(error) }
    }

    private fun setupRecyclerView()
    {
        mBinding.charactersRecyclerview.setHasFixedSize(true)
        mCharacterAdapter.setListener(this)
        mBinding.charactersRecyclerview.adapter = mCharacterAdapter
    }

    private fun setupTrailerListener(url: String?) {
        url?.let {
            val youtubeIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(url))

            mBinding.trailerCardview.setOnClickListener {
                try {
                    startActivity(youtubeIntent)
                } catch (ex: ActivityNotFoundException) {
                    ex.printStackTrace()
                }
            }
        }
    }

    override fun populateRecyclerView(characters: List<Character>?) {
        mCharacterAdapter.setDataset(characters)
        hideProgress()
    }

    override fun setInformation(anime: Anime?) {
        showProgress()
        mBinding.titleMaterialtextview.text = anime?.title
        mBinding.scoreMaterialtextview.text = anime?.score.toString()
        mBinding.popularityMaterialtextview.text = anime?.popularity.toString()
        mBinding.rankedMaterialtextview.text = anime?.rank.toString()
        mBinding.airedMaterialtextview.text = anime?.aired?.string
        mBinding.statusMaterialtextview.text = anime?.status
        mBinding.broadcastMaterialtextview.text = anime?.broadcast?.string
        var studios = ""
        anime?.studios?.forEach { it -> studios += it.name }
        mBinding.studiosMaterialtextview.text = studios
        mBinding.ratingMaterialtextview.text = anime?.rating.toString()
        anime?.episodes?.let {
            mBinding.episodesMaterialtextview.text = it.toString()
        }
        mBinding.durationMaterialtextview.text = anime?.duration.toString()
        mBinding.membersMaterialtextview.text = anime?.members.toString()
        mBinding.synopsisMaterialtextview.text = anime?.synopsis.toString()

        mBinding.genresChipgroup.removeAllViews()
        anime?.genres?.forEach {
            val chip = Chip(mContext)
            chip.text = it.name
            chip.setChipBackgroundColorResource(R.color.red_200)
            mBinding.genresChipgroup.addView(chip)
        }

        mImageUrl = anime?.images?.jpg?.largeImage
        Glide.with(mContext)
            .load(anime?.images?.jpg?.url)
            .centerCrop()
            .into(mBinding.bannerImageview)

        anime?.trailer?.let { trailer ->

            trailer.images?.largeImage?.let { largeImage ->

                mBinding.trailerCardview.visibility = ViewGroup.VISIBLE
                Glide.with(mContext)
                    .load(largeImage)
                    .centerCrop()
                    .into(mBinding.trailerImageview)
            }
            setupTrailerListener(trailer.url)
        }

        mCharactersViewModel.getCharacters(anime?.id!!)
    }

    override fun showProgress() {
        mBinding.progressLinearprogressindicator.visibility = ViewGroup.VISIBLE
    }

    override fun hideProgress() {
        mBinding.progressLinearprogressindicator.visibility = ViewGroup.GONE
    }

    override fun onError(error: ServiceError) {
        hideProgress()
        val alert = AlertDialog.Builder(mContext)
            .setMessage(error.message)
            .setPositiveButton("Accept"){ dialog, _ ->
                dialog.dismiss()
            }
            .create()
        alert.show()
    }

    override fun onCharacterSelected(id: Int?) {
        val args = CharacterInformationFragment.getArgumentBundle(id!!, mImageUrl!!)
        findNavController().navigate(R.id.navigate_to_character_detail_fragment, args)
    }

    companion object {

        const val ANIME_ID_KEY = "com.creative.apps.kento.presentation.detail.DetailAnimeFragment.AnimeIdKey"

        fun getBundleArgs(id : Int) : Bundle {
            val args = Bundle()
            args.putInt(ANIME_ID_KEY, id)
            return args
        }

    }

}