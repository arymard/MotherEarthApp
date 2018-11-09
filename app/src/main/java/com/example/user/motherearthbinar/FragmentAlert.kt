package com.example.user.motherearthbinar


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentAlert : Fragment() {
        private lateinit var viewPager : ViewPager
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_alert, container, false)

        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            viewPager = view?.findViewById(R.id.viewpager1)
            setupViewPager(viewPager)
        }
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.addFragment(Alert_1(),"satu")
        adapter.addFragment(Alert_2(),"dua")
        viewPager.adapter = adapter
    }

}