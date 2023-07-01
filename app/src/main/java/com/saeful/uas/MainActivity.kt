package com.saeful.uas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.saeful.uas.databinding.ActivityMainBinding
import com.saeful.uas.fragment.AboutFragment
import com.saeful.uas.fragment.FavoriteFragment
import com.saeful.uas.fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val fragmentHome = HomeFragment()
        val favoriteFragment = FavoriteFragment()
        val aboutFragment = AboutFragment()

        setDisplay(fragmentHome)
        binding.botnav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.favorit -> setDisplay(favoriteFragment)
                R.id.home -> setDisplay(fragmentHome)
                R.id.about -> setDisplay(aboutFragment)
            }
            true
        }
    }

    private fun setDisplay(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_layout,fragment)
            commit()
        }
    }
}