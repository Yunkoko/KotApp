package com.firstest.kotapp.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstest.kotapp.R
import com.firstest.kotapp.domain.entity.DnDSpell
import com.firstest.kotapp.domain.listusecase.ListAdapter
import org.koin.android.ext.android.inject

class MainActivity: AppCompatActivity() {

    private val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.makeAPICall()
        mainViewModel.apiLiveData.observe(this, Observer {
            when(it) {
                is APICallSuccess -> {
                    showList(it.spellList)
                }
                APICallFailure -> {
                    Toast.makeText(this, "API Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showList(spells: List<DnDSpell>){
        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val myAdapter = ListAdapter(spells)
        print(myAdapter.itemCount)
        recyclerView.adapter = myAdapter
    }
}