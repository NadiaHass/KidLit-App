package com.example.kidlit

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kidlit.databinding.ActivityBookBinding

class BookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fileResourceId = intent.getIntExtra("bookResourceId" , 0)
        val title = intent.getStringExtra("bookTitle")

        try {
            val text = readTextFromRaw(this , fileResourceId)

            binding.tvTitle.text = title
            binding.tvDescription.text = text

        }catch (e : Exception){

        }

    }


    private fun readTextFromRaw(context: Context, rawResourceId: Int): String {
        return context.resources.openRawResource(rawResourceId).bufferedReader().use { it.readText() }
    }
}