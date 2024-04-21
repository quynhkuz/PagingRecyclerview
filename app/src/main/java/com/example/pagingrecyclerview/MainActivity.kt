package com.example.pagingrecyclerview

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    var isLoading = false
    var isLastPage = false
    var currentPage = 1
    var totalPage = 5


    lateinit var listPaging : MutableList<String>
    lateinit var lisdata : MutableList<String>
    lateinit var adapter : DataAdapter

    lateinit var rcv :RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        rcv = findViewById(R.id.rcv)
        listPaging = mutableListOf()
        addData()
        loadData()
        adapter = DataAdapter(listPaging)
        val layout = LinearLayoutManager(this)
        rcv.layoutManager = layout
        rcv.adapter = adapter
        rcv.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        rcv.addOnScrollListener(object : PaginationScrollListener(layout) {
            override fun loadMoreItem() {

                loadData()
                adapter.notifyDataSetChanged()

            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }
        })

    }



    private fun addData()
    {
        lisdata = mutableListOf()
        for (i in 0..99)
        {
            lisdata.add("item ${i+1}")
        }
    }


    fun loadData()
    {
        data@ for (i in listPaging.size .. listPaging.size+14)
        {
            Log.d("QQQ","$i/${lisdata.size}")
            if (i > lisdata.size-1)
            {
                isLastPage  =true
                break@data
            }

            listPaging.add(lisdata[i])

        }

    }


}