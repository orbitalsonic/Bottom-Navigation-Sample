package com.orbitalsonic.bottomnavsample.presentation.extras.language.ui

import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.orbitalsonic.bottomnavsample.R
import com.orbitalsonic.bottomnavsample.common.koin.DiComponent
import com.orbitalsonic.bottomnavsample.common.repository.LanguageRepository
import com.orbitalsonic.bottomnavsample.databinding.FragmentLanguageBinding
import com.orbitalsonic.bottomnavsample.helpers.listener.RapidSafeListener.setOnRapidClickSafeListener
import com.orbitalsonic.bottomnavsample.helpers.locale.applyLanguage
import com.orbitalsonic.bottomnavsample.helpers.navigation.popFrom
import com.orbitalsonic.bottomnavsample.helpers.ui.hideKeyboard
import com.orbitalsonic.bottomnavsample.presentation.base.fragments.BaseFragment
import com.orbitalsonic.bottomnavsample.presentation.extras.language.adapter.AdapterLanguage
import com.orbitalsonic.bottomnavsample.presentation.extras.language.model.LanguageItem
import com.orbitalsonic.bottomnavsample.storage.provider.DpLanguages

class LanguageFragment :
    BaseFragment<FragmentLanguageBinding>(FragmentLanguageBinding::inflate) {

    private val dpLanguages by lazy { DpLanguages() }
    private val sharedPrefManager by lazy { DiComponent().sharedPrefManager }

    private val adapterLanguage by lazy {
        AdapterLanguage { languageItem ->
            onLanguageSelected(languageItem)
        }
    }

    private var selectedLanguage: LanguageItem? = null

    override fun onViewCreated() {
        initRecyclerView()
        setupClicks()
        setupListener()
    }

    private fun setupClicks() {
        binding.btnBack.setOnRapidClickSafeListener {
            popFrom(R.id.languageFragment)
        }

        binding.btnApply.setOnRapidClickSafeListener {
            onApplyClick()
        }
    }

    private fun setupListener() {
        binding.etSearchText.apply {
            doAfterTextChanged {
                searchLanguage(it.toString())
            }
        }
    }

    private fun initRecyclerView() {
        binding.languageRecyclerview.adapter = adapterLanguage
        loadLanguages()
    }

    private fun loadLanguages() {
        val list = dpLanguages.getLanguagesList(sharedPrefManager.appLanguageCode)
        adapterLanguage.submitList(list)
    }

    private fun onApplyClick() {
        selectedLanguage?.let {
            if (sharedPrefManager.appLanguageCode != it.languageCode) {

                sharedPrefManager.appLanguageCode = it.languageCode

                activity?.hideKeyboard()

                applyLanguage(sharedPrefManager.appLanguageCode)
            } else {
                popFrom(R.id.languageFragment)
            }
        } ?: run {
            popFrom(R.id.languageFragment)
        }
    }

    private fun onLanguageSelected(languageItem: LanguageItem) {

        selectedLanguage = languageItem

        val updatedList = dpLanguages.getLanguagesList(languageItem.languageCode)
        adapterLanguage.submitList(updatedList)
    }

    private fun searchLanguage(query: String) {
        LanguageRepository.searchLanguage(query, sharedPrefManager.appLanguageCode) {
            try {
                if (it.isEmpty()) {
                    binding.tvNothingFound.visibility = View.VISIBLE
                } else {
                    binding.tvNothingFound.visibility = View.GONE
                }
                adapterLanguage.submitList(it)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

}