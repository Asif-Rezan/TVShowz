package com.asifrezan.tvshowz.fregments

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.asifrezan.tvshowz.R
import com.asifrezan.tvshowz.adapters.FragmentAdapter
import com.asifrezan.tvshowz.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.fragment_home, container, false)

        binding = FragmentHomeBinding.inflate(layoutInflater)
        val view = binding.root



        viewPager = binding.viewPager
        tabLayout = binding.tabLayout



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = listOf(
            MoviesFragment(),
            TvSeriesFragment(),
            DocumentaryFragment(),
            SportsFragment(),
        )

        viewPager.adapter = FragmentAdapter(requireActivity(), fragmentList)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            when (position) {
                0 -> tab.text = "Movies"
                1 -> tab.text = "Tv Series"
                2 -> tab.text = "Documentary"
                3 -> tab.text = "Sport"
            }

        }.attach()







    }


}