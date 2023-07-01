package com.saeful.uas.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saeful.uas.Data.DataModel
import com.saeful.uas.DetailActivity
import com.saeful.uas.R
import com.saeful.uas.adapter.PopulerAdapter
import com.saeful.uas.adapter.RecomAdapter
import java.util.Locale

class HomeFragment : Fragment() {
    private lateinit var rv_recom : RecyclerView
    private lateinit var rv_populer : RecyclerView
    private lateinit var searchview: SearchView
    private lateinit var recomAdapter: RecomAdapter
    private lateinit var populerAdapter: PopulerAdapter
    private val list  = ArrayList<DataModel>()
    private val listPopuler = ArrayList<DataModel>()

    companion object{
        const val EXTRA_DATA = "data"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recomended(view)
        populer(view)
        searchview(view)
    }

    private fun searchview(view: View) {
        searchview = view.findViewById(R.id.searchview)
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(newText: String?) {
        filterRecomended(newText)
        filterPopuler(newText)
    }

    private fun filterPopuler(newText: String?) {
        if (newText != null){
            val filteredlistPopuler = ArrayList<DataModel>()
            for (i in list){
                if (i.title.lowercase(Locale.ROOT).contains(newText)){
                    filteredlistPopuler.add(i)
                }
            }
            if (filteredlistPopuler.isEmpty()){
                Toast.makeText(context,"Data Not Found", Toast.LENGTH_SHORT).show()
            }else{
                populerAdapter.setFiltered(filteredlistPopuler)
            }
        }
    }

    private fun filterRecomended(newText: String?) {
        if (newText != null){
            val filteredlistRecomended = ArrayList<DataModel>()
            for (i in list){
                if (i.title.lowercase(Locale.ROOT).contains(newText)){
                    filteredlistRecomended.add(i)
                }
            }
            if (filteredlistRecomended.isEmpty()){
                Toast.makeText(context,"Data Not Found", Toast.LENGTH_SHORT).show()
            }else{
                recomAdapter.setFiltered(filteredlistRecomended)
            }
        }
    }

    private fun populer(view: View) {
        rv_populer = view.findViewById(R.id.rv_populer)
        rv_populer.setHasFixedSize(true)
        listPopuler.addAll(getPopuler())
        rv_populer.layoutManager = LinearLayoutManager(context)
        populerAdapter = PopulerAdapter(listPopuler)
        rv_populer.adapter = populerAdapter
        populerAdapter.setOnItemClickListener(object : PopulerAdapter.onItemClickListener{
            override fun onItemClicked(position: Int) {
                val intent = Intent(context,DetailActivity::class.java)
                intent.putExtra(HomeFragment.EXTRA_DATA,listPopuler[position])
                startActivity(intent)
            }
        })
    }

    private fun recomended(view: View) {
        rv_recom = view.findViewById(R.id.rv_recom)
        rv_recom.setHasFixedSize(true)
        list.addAll(getRecomended())
        rv_recom.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recomAdapter = RecomAdapter(list)
        rv_recom.adapter = recomAdapter
        recomAdapter.setOnItemClickListener(object : RecomAdapter.onItemClickListener{
            override fun onItemClicked(position: Int) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(HomeFragment.EXTRA_DATA,list[position])
                startActivity(intent)
            }
        })
    }


    private fun getPopuler(): Collection<DataModel> {
        val listBeach = ArrayList<DataModel>()
        val data_heading = resources.getStringArray(R.array.judul)
        val data_address = resources.getStringArray(R.array.alamat)
        val data_rating = resources.getStringArray(R.array.rating)
        val data_jumlah = resources.getStringArray(R.array.jumlah)
        val data_desc = resources.getStringArray(R.array.desc)
        val data_image = resources.obtainTypedArray(R.array.image_two)

        for (i in data_heading.indices){
            val data = DataModel(data_image.getResourceId(i,-1),data_heading[i],data_address[i],data_rating[i],data_desc[i],data_jumlah[i])
            listBeach.add(data)
        }
        return listBeach
    }

    @SuppressLint("Recycle")
    private fun getRecomended(): ArrayList<DataModel> {
        val listBeach = ArrayList<DataModel>()
        val data_heading = resources.getStringArray(R.array.judul)
        val data_address = resources.getStringArray(R.array.alamat)
        val data_rating = resources.getStringArray(R.array.rating)
        val data_jumlah = resources.getStringArray(R.array.jumlah)
        val data_desc = resources.getStringArray(R.array.desc)
        val data_image = resources.obtainTypedArray(R.array.foto)

        for (i in data_heading.indices){
            val data = DataModel(data_image.getResourceId(i,-1),data_heading[i],data_address[i],data_rating[i],data_desc[i],data_jumlah[i])
            listBeach.add(data)
        }
        return listBeach
    }
}