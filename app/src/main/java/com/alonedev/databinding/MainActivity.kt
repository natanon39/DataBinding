package com.alonedev.databinding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.alonedev.databinding.adapter.TabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTabAdapter()
    }


    private fun setTabAdapter()
    {
        val adapter = TabAdapter(supportFragmentManager,lifecycle)

        val pager = findViewById<ViewPager2>(R.id.viewPager_main)
        pager.adapter = adapter
        val tab = findViewById<TabLayout>(R.id.tabLayout_main)
        TabLayoutMediator(
            tab,
            pager
        )
        { tab, position ->
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.ic_baseline_home_24)
                    tab.text = "หน้าหลัก"
                }
                1 -> {
                    tab.setIcon(R.drawable.ic_baseline_account_circle_24)
                    tab.text = "บัญชี"
                }
            }
        }.attach()
        pager.setUserInputEnabled(false);
    }
}