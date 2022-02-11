package com.creative.apps.kento.presentation.detail

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.creative.apps.kento.R
import com.creative.apps.kento.databinding.FragmentCharacterInformationBinding
import com.creative.apps.kento.domain.local.ServiceError
import com.creative.apps.kento.domain.remote.characters.CharacterInformation
import com.creative.apps.kento.framework.viewmodels.CharacterInformationViewModel
import com.creative.apps.kento.framework.viewmodels.contracts.CharacterInformationContract
import dagger.hilt.android.AndroidEntryPoint
import java.net.IDN
import javax.inject.Inject

@AndroidEntryPoint
class CharacterInformationFragment : Fragment(), CharacterInformationContract.CharacterInformationInterfaceView {

    @Inject lateinit var mCharacterViewModel : CharacterInformationViewModel
    private lateinit var mBinding : FragmentCharacterInformationBinding
    private lateinit var mContext : Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = requireContext()
        mBinding = FragmentCharacterInformationBinding.inflate(LayoutInflater.from(mContext), container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        val id = arguments?.getInt(CHARACTER_ID_KEY,0)
        val url = arguments?.getString(URL_IMAGE_KEY)

        setupCharacterViewModel(id!!)
        setupAnimeImage(url!!)
    }

    private fun setupCharacterViewModel(id : Int) {
        mCharacterViewModel.characterInformation.observe(viewLifecycleOwner) {
            setCharacterInformation(it)
        }
        mCharacterViewModel.error.observe(viewLifecycleOwner) {
            onError(it)
        }
        showProgress()
        mCharacterViewModel.getCharacterInformation(id)
    }

    private fun setupAnimeImage(url : String) {
        hideProgress()
        val radius = context?.resources?.getDimension(R.dimen.corner_radius)

        val shapeAppearanceModel = mBinding.bannerShapeableimageview.shapeAppearanceModel.toBuilder()
            .setAllCornerSizes(radius!!)
            .build()

        mBinding.bannerShapeableimageview.shapeAppearanceModel = shapeAppearanceModel

        Glide.with(mContext)
            .load(url)
            .centerCrop()
            .into(mBinding.bannerShapeableimageview)
    }

    override fun setCharacterInformation(character: CharacterInformation?) {
        mBinding.nameMaterialtextview.text = character?.name
        mBinding.aboutMaterialtextview.text = character?.about

        val radius = context?.resources?.getDimension(R.dimen.full_round_radius)

        val shapeAppearanceModel = mBinding.bannerShapeableimageview.shapeAppearanceModel.toBuilder()
            .setAllCornerSizes(radius!!)
            .build()

        mBinding.characterShapeableimageview.shapeAppearanceModel = shapeAppearanceModel

        Glide.with(mContext)
            .load(character?.images?.jpg?.url)
            .centerCrop()
            .into(mBinding.characterShapeableimageview)
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

    companion object {

        private const val CHARACTER_ID_KEY = "com.creative.apps.kento.presentation.detail.CharacterInformationFragment.CharacterIdKey"
        private const val URL_IMAGE_KEY = "com.creative.apps.kento.presentation.detail.CharacterInformationFragment.UrlImageKey"

        fun getArgumentBundle(id : Int, urlImage : String) : Bundle {
            val args = Bundle(0)
            args.putString(URL_IMAGE_KEY, urlImage)
            args.putInt(CHARACTER_ID_KEY, id)
            return args
        }
    }
}