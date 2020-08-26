package com.shubham.criminalintent.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shubham.criminalintent.Fragment.CrimeFragment
import com.shubham.criminalintent.Fragment.CrimeListFragment
import com.shubham.criminalintent.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null){
            val fragment= CrimeListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container,fragment)
                .commit()
        }


    }
}