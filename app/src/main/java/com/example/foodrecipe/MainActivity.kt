package com.example.foodrecipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.foodrecipe.adapter.PoppularItemAdapter
import com.example.foodrecipe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var arrayList: ArrayList<Recipe>
    lateinit var adapter: PoppularItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setuprecyclerview()
        binding.search.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,SearchActivity::class.java))
        })
        binding.salad.setOnClickListener(View.OnClickListener {
            var intent=Intent(this,AllItemCategory::class.java)
            intent.putExtra("categoryname","Salad")
            startActivity(intent)
        })
        //main==dish
        binding.main.setOnClickListener(View.OnClickListener {
            var intent=Intent(this,AllItemCategory::class.java)
            intent.putExtra("categoryname","Dish")
            startActivity(intent)

        })
        binding.drinks.setOnClickListener(View.OnClickListener {
            var intent=Intent(this,AllItemCategory::class.java)
            intent.putExtra("categoryname","Drinks")
            startActivity(intent)

        })
        //cake==desserts
        binding.cake.setOnClickListener(View.OnClickListener {
            var intent=Intent(this,AllItemCategory::class.java)
            intent.putExtra("categoryname","Desserts")
            startActivity(intent)

        })
        binding.imageView.setOnClickListener(View.OnClickListener {
            var bottom=Bottomfragment()
            bottom.show(supportFragmentManager,"tag")
        })
    }

    private fun setuprecyclerview() {
        arrayList= ArrayList()
        binding.recycle.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        var db=Room.databaseBuilder(this,DatabaseApp::class.java,"db_name").
                allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("databases/recipe.db")
            .build()
        var daoobject=db.getDao()
        var recipe=daoobject.getall()
        for( data in recipe!!.indices){
            if(recipe[data]!!.category.contains("Popular")){
                arrayList.add(recipe[data]!!)
            }
        }
        adapter=PoppularItemAdapter(arrayList,this)
        binding.recycle.adapter=adapter
    }
}


