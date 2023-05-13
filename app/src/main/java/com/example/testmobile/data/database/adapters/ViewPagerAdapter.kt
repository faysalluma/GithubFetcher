package com.example.testmobile.data.database.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.testmobile.presentation.detail.branch.BranchFragment
import com.example.testmobile.presentation.detail.contributor.ContributorFragment

class ViewPagerAdapter(fm: FragmentManager, var tabCount: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> BranchFragment()
            1 -> ContributorFragment()
            else -> BranchFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}