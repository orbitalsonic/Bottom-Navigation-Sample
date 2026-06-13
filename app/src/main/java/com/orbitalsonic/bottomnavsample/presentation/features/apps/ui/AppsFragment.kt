package com.orbitalsonic.bottomnavsample.presentation.features.apps.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.orbitalsonic.bottomnavsample.R
import com.orbitalsonic.bottomnavsample.databinding.FragmentAppsBinding
import com.orbitalsonic.bottomnavsample.presentation.base.fragments.BaseFragment
import com.orbitalsonic.bottomnavsample.presentation.features.apps.adapter.AdapterAppsTabPager

class AppsFragment : BaseFragment<FragmentAppsBinding>(FragmentAppsBinding::inflate) {

    private var tabLayoutMediator: TabLayoutMediator? = null

    override fun onViewCreated() {
        setupTabs()
    }

    private fun setupTabs() {
        val adapter = AdapterAppsTabPager(this)
        binding.viewPager.adapter = adapter

        // Attach TabLayout with ViewPager2
        tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.customView = createTabView(position) // Use custom tab view
            }
        tabLayoutMediator?.attach()

        // Add page change listener
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private fun createTabView(position: Int): View {
        val tabView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab, null)
        val tabIcon = tabView.findViewById<ImageView>(R.id.tab_icon)
        val tabText = tabView.findViewById<TextView>(R.id.tab_text)

        when (position) {
            0 -> {
                tabText.text = getString(R.string.user_apps)
                tabIcon.setImageResource(R.drawable.ic_user_apps)
            }

            1 -> {
                tabText.text = getString(R.string.system_apps)
                tabIcon.setImageResource(R.drawable.ic_system_apps)
            }
        }
        return tabView
    }

    // Page change listener
    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            // You can log the page selection or perform any action here
            Log.d("ViewPager2", "Selected Page: $position")
        }
    }

    override fun onDestroyView() {
        tabLayoutMediator?.detach() // Remove TabLayoutMediator to prevent memory leaks
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback) // Unregister listener
        super.onDestroyView()
    }
}