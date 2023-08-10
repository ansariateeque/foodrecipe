package com.example.foodrecipe.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipe.Recipe
import com.example.foodrecipe.RecipeDescription
import com.example.foodrecipe.databinding.AllitemcategorylayoutBinding
import com.squareup.picasso.Picasso

class AllItemCategoryAdapter(var arrayList: ArrayList<Recipe>,var context: Context) :RecyclerView.Adapter<AllItemCategoryAdapter.MyviewHolder>(){
    class MyviewHolder(var binding:AllitemcategorylayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
       return MyviewHolder(AllitemcategorylayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        var recipe=arrayList.get(position)
       holder.binding.tittle.setText(recipe.tittle)
        Picasso.get().load(recipe.img).into(holder.binding.foodimage)
        var time = recipe.ing.split("\n".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()
        holder.binding.time.setText(time.get(0))
        holder.binding.descriptionpage.setOnClickListener(View.OnClickListener {
            var intent= Intent(context,RecipeDescription::class.java)
            intent.putExtra("tittle",recipe.tittle)
            intent.putExtra("ing",recipe.ing)
            intent.putExtra("des",recipe.des)
            intent.putExtra("img",recipe.img)
            context.startActivity(intent)
        })
    }
}