package com.example.foodrecipe.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipe.Recipe
import com.example.foodrecipe.databinding.SearchLayoutBinding
import com.squareup.picasso.Picasso
import kotlin.math.log

class SearchlayoutAdapter(var arrayList: ArrayList<Recipe>,var context: Context):RecyclerView.Adapter<SearchlayoutAdapter.MyViewHolder>() {
    class MyViewHolder(var binding:SearchLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
     return MyViewHolder(SearchLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    fun filterListas(filteredlist: ArrayList<Recipe>) {
        // below line is to add our filtered
        // list in our course array list.
        arrayList = filteredlist
        // below line is to notify our
        // adapter
        Log.d("hi", "filterListas: ")
        // as change in recycler view data.
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      var recipe=arrayList.get(position)
        Picasso.get().load(recipe.img).into(holder.binding.foodimage)
        holder.binding.tittle.setText(recipe.tittle)
    }


}