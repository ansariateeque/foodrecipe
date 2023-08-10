package com.example.foodrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodrecipe.adapter.AllItemCategoryAdapter
import com.example.foodrecipe.adapter.PoppularItemAdapter
import com.example.foodrecipe.databinding.ActivityAllItemCategoryBinding

class AllItemCategory : AppCompatActivity() {
    lateinit var arrayList: ArrayList<Recipe>
    lateinit var adapter:AllItemCategoryAdapter
    lateinit var binding:ActivityAllItemCategoryBinding
     var category:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAllItemCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cardView.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        if(category=="Dish"){
            category="Main Dish"
        }
       category= intent.getStringExtra("categoryname").toString()
        binding.categoryname.setText(category)
        setuprecyclerview()

    }
    private fun setuprecyclerview() {
        arrayList= ArrayList()
        binding.recyle.layoutManager=
            LinearLayoutManager(this)
        var db= Room.databaseBuilder(this,DatabaseApp::class.java,"db_name").
        allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("databases/recipe.db")
            .build()
        var daoobject=db.getDao()
        var recipe=daoobject.getall()
        for( data in recipe!!.indices){
            if(recipe[data]!!.category.contains(category)){
                arrayList.add(recipe[data]!!)
            }
        }
        adapter= AllItemCategoryAdapter(arrayList,this)
        binding.recyle.adapter=adapter
    }
}