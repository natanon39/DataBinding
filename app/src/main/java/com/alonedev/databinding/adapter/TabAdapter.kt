package com.alonedev.databinding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alonedev.databinding.fragment.AccountFragment
import com.alonedev.databinding.fragment.HomeFragment

class TabAdapter(fm:FragmentManager,lf:Lifecycle): FragmentStateAdapter(fm,lf) {
    override fun getItemCount(): Int =2

    override fun createFragment(position: Int): Fragment = when(position)
    {
        0 -> HomeFragment()
        else ->AccountFragment()
    }
}