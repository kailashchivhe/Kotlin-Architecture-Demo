package com.kai.kotlinmvp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kai.kotlinmvp.R
import com.kai.kotlinmvp.model.Author
import kotlinx.android.synthetic.main.item_author_list_recycler.view.*


class AuthorListAdapter(private val mAuthorList: List<Author>, private val listener: (Author) -> Unit) : RecyclerView.Adapter< AuthorListAdapter.AuthorViewHolder >()
{
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): AuthorViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_author_list_recycler, parent, false)
        return AuthorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int)
    {
        holder.bind(mAuthorList[position], listener)
    }

    override fun getItemCount(): Int
    {
        return mAuthorList.size
    }

    class AuthorViewHolder( itemView: View ) : RecyclerView.ViewHolder( itemView )
    {
        fun bind( authorItem: Author, listener: (Author) -> Unit) = with( itemView )
        {
            textView.text = authorItem.authorName
//            imageView.setImageBitmap( NetworkHelper.getBitMap( "https://picsum.photos/300/300/?image=${authorItem.authorId}" ) )
            Glide.
                with(imageView.context).
                load( "https://picsum.photos/300/300/?image=${authorItem.authorId}" ).
                placeholder(R.drawable.default_image).
                into( imageView )
            itemView.setOnClickListener{ listener(authorItem) }
        }
    }
}