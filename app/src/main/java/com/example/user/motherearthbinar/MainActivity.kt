package com.example.user.motherearthbinar

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_acttivity.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.gempa -> {
                changeFragment(GempaFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.cuaca -> {
                changeFragment(CuacaFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.berita -> {
                changeFragment(BeritaFragment())
                return@OnNavigationItemSelectedListener true
            }


            R.id.quake_alert -> {
                changeFragment(FragmentAlert())
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_acttivity)
        changeFragment(GempaFragment())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
    }





}




