package com.saeful.uas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saeful.uas.Data.DataModel
import com.saeful.uas.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object{
        const val EXTRA_PERSON = "data"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var data = intent.getParcelableExtra<DataModel>(EXTRA_PERSON)
        if (data != null) {
            binding.ivDetail.setImageResource(data.image)
            binding.tvHeading.text = data.title
            binding.tvDetail.text = data.desc
        }
    }
}