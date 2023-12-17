package com.example.kidlit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val bookList: List<Book> , private val context : Context) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        val imageView: ImageView = itemView.findViewById(R.id.iv_book)
        val descriptionTextView: TextView = itemView.findViewById(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentBook = bookList[position]
        holder.titleTextView.text = currentBook.title
        holder.imageView.setImageResource(currentBook.imageResourceId)
        holder.descriptionTextView.text = currentBook.description

        holder.itemView.setOnClickListener{
            val intent = Intent(context , BookActivity::class.java)
            intent.putExtra("bookResourceId", bookList[position].FileResourceId)
            intent.putExtra("bookTitle", bookList[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}
