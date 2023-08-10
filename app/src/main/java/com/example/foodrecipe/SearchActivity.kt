package com.example.foodrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View

import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodrecipe.adapter.SearchlayoutAdapter
import com.example.foodrecipe.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    lateinit var arrayList: ArrayList<Recipe>
    lateinit var adapter: SearchlayoutAdapter

    lateinit var recipe: List<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.search.requestFocus()
        binding.back.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        var db =
            Room.databaseBuilder(this, DatabaseApp::class.java, "db_name").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .createFromAsset("databases/recipe.db")
                .build()
        var daoobject = db.getDao()
         recipe = daoobject.getall()!!
        setuprecyclerview()
      binding.search.addTextChangedListener(object : TextWatcher {
          override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

          }

          override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if(p0.toString()!=""){
                filterList(p0.toString())
            }
          }

          override fun afterTextChanged(p0: Editable?) {

          }
      })

    }

    private fun filterList(s: String) {
       var  filterArrayList = ArrayList<Recipe>()

        for (item in recipe.indices) {
            if (recipe[item]!!.tittle.toLowerCase()
                    .contains(s.lowercase())
            ) {
                // if the item is matched we are
                // adding it to our filtered list.
                filterArrayList.add(recipe[item]!!)
            }
        }
            adapter.filterListas(filterArrayList)

    }

    private fun setuprecyclerview() {
        arrayList = ArrayList()
        binding.recyle.layoutManager =
            LinearLayoutManager(this)

        for (data in recipe!!.indices) {
            arrayList.add(recipe[data]!!)
        }
        adapter = SearchlayoutAdapter(arrayList, this)
        binding.recyle.adapter = adapter
    }
}