package com.shubham.criminalintent.Model

import android.util.Log
import androidx.lifecycle.ViewModel

private val TAG = "CrimeListViewModel"
class CrimeListViewModel:ViewModel() {
    val crimes = mutableListOf<Crime>()
    init {
        Log.d(TAG,"ViewModel created")
        for (i in 0 until 100){
            val crime = Crime()
            crime.title = "Crime ${i}"
            crime.isSolved = i % 2 == 0
            if (i%7==0){
                crime.requiresPolice=true
            }
            crimes += crime
        }

    }
}