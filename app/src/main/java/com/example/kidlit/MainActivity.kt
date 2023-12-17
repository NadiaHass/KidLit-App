package com.example.kidlit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidlit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: BookAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var bookList : List<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


         bookList = listOf(
            Book("Diary of a Wimpy Kid", R.drawable.img1, R.raw.book , "Up until now, middle school hasn’t exactly been a joyride for Greg Heffley. So when the town threatens to close the crumbling building..." ),
            Book("Harry Potter and the Prisoner", R.drawable.img2, R.raw.book , "When the Knight Bus crashes through the darkness and screeches to a halt in front of him, it's the start of another far from..." ) ,
            Book("Legend of the Star Dragon", R.drawable.img3, R.raw.book , "This series is part of Scholastic's early chapter book line, Branches, aimed at newly independent readers. With easy-to-read text..." ) ,
            Book("Cat Kid Comic Club", R.drawable.img4, R.raw.book , "The Cat Kid Comic Club is in crisis! After learning that one of their comics will be published, the baby frogs are filled with anxiety..." )
        )

        setupAdapter(bookList)


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterBooks(newText)
                return true
            }
        })


    }

    private fun setupAdapter(bookList: List<Book>) {
        adapter = BookAdapter(bookList , this@MainActivity)
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        binding.rvBooks.adapter = adapter
    }

    private fun filterBooks(query: String?) {
        val filteredList = mutableListOf<Book>()

        for (book in bookList) {
            if (book.title.contains(query.orEmpty(), ignoreCase = true) ||
                book.description.contains(query.orEmpty(), ignoreCase = true)
            ) {
                filteredList.add(book)
            }
        }

        setupAdapter(filteredList)
    }
}
