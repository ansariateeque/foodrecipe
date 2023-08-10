package com.example.foodrecipe

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.foodrecipe.databinding.ActivityRecipeDescriptionBinding
import com.squareup.picasso.Picasso

class RecipeDescription : AppCompatActivity() {
    lateinit var binding: ActivityRecipeDescriptionBinding
    var check = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        var tittle = intent.getStringExtra("tittle")
        var ing = intent.getStringExtra("ing")
        var des = intent.getStringExtra("des")
        var img = intent.getStringExtra("img")
        Picasso.get().load(img)
            .into(binding.foodimage)

        binding.tittle.setText(tittle)
        var fulingredient = ing?.split("\n".toRegex())?.dropLastWhile {
            it.isEmpty()
        }?.toTypedArray()
        binding.time.setText(fulingredient?.get(0))

        for (data in fulingredient!!.indices) {
            if (data >= 1) {
                binding.ingdes.text =
                    """${binding.ingdes.text}  🟢  ${fulingredient[data]} 
 
                    """.trimIndent()
            }

        }


        binding.ingredient.setOnClickListener(View.OnClickListener {
            binding.ingredient.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.steps.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.ingredient.setBackgroundResource(R.drawable.textview)
            binding.steps.setBackgroundResource(0)
            binding.ingdes.text = ""
            for (data in fulingredient!!.indices) {
                if (data >= 1) {
                    binding.ingdes.text =
                        """${binding.ingdes.text}  🟢  ${fulingredient[data]} 
 
                        """.trimIndent()
                }

            }

        })
        binding.steps.setOnClickListener(View.OnClickListener {
            binding.steps.setTextColor(ContextCompat.getColor(this, R.color.white));
            binding.ingredient.setTextColor(ContextCompat.getColor(this, R.color.black));
            binding.steps.setBackgroundResource(R.drawable.textview)
            binding.ingredient.setBackgroundResource(0)
            binding.ingdes.text = ""
            binding.ingdes.text = des.toString()


        })

        binding.zoomout.setOnClickListener(View.OnClickListener {
            if (check) {
                binding.foodimage.scaleType = ImageView.ScaleType.FIT_CENTER
                check = false
            } else {
                binding.foodimage.scaleType = ImageView.ScaleType.CENTER_CROP
                check = true
            }
        })


    }


}