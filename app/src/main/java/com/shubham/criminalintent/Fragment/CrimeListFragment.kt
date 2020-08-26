package com.shubham.criminalintent.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shubham.criminalintent.Model.Crime
import com.shubham.criminalintent.Model.CrimeListViewModel
import com.shubham.criminalintent.R

private const val TAG = "CrimeListFragment"

class CrimeListFragment:Fragment() {

    private lateinit var crimeRecyclerView:RecyclerView
    private var adapter:CrimeAdapter?=null

    private val crimeListViewModel:CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"Total crimes: ${crimeListViewModel.crimes.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list,container,false)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view)
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()
        return view
    }

    private inner class crimeHolder(view:View):RecyclerView.ViewHolder(view),View.OnClickListener{
        private lateinit var crime:Crime
        private val titleTextView:TextView = itemView.findViewById(R.id.crime_title)
        private val dateTextView:TextView = itemView.findViewById(R.id.crime_date)

        init {
            itemView.setOnClickListener(this)
        }
        fun bind(crime:Crime){
            this.crime = crime
            titleTextView.text = crime.title
            dateTextView.text = crime.date.toString()

        }

        override fun onClick(v: View?) {
            Toast.makeText(context,"${crime.title} pressed!",Toast.LENGTH_SHORT).show()
        }
    }

    private  inner class CrimeAdapter(var crimes:List<Crime>):RecyclerView.Adapter<crimeHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): crimeHolder {
            val view:View
            if (viewType==1){
                view = layoutInflater.inflate(R.layout.list_item_crime_serious,parent,false)
            }
            else{
                view = layoutInflater.inflate(R.layout.list_item_crime,parent,false)
            }

            return crimeHolder(view)
        }

        override fun onBindViewHolder(holder: crimeHolder, position: Int) {
            holder.bind(crimes[position])
        }

        override fun getItemCount(): Int {
            return crimes.size
        }

        override fun getItemViewType(position: Int): Int {
            val crime = crimes[position]
            if (crime.requiresPolice){
                return 1
            }
            else{
                return 0
            }
        }



    }
    private fun updateUI() {
        val crimes = crimeListViewModel.crimes
        adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter
    }



    companion object{
        fun newInstance():CrimeListFragment{
            return  CrimeListFragment()
        }
    }

}