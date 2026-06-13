package com.orbitalsonic.bottomnavsample.presentation.features.apps.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.orbitalsonic.bottomnavsample.presentation.features.apps.ui.SystemAppsFragment
import com.orbitalsonic.bottomnavsample.presentation.features.apps.ui.UserAppsFragment

class AdapterAppsTabPager(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2 // Total number of tabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserAppsFragment()
            1 -> SystemAppsFragment()
            else -> UserAppsFragment()
        }
    }
}
