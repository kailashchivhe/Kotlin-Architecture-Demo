package com.kai.kotlinmvp.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kai.kotlinmvp.R
import com.kai.kotlinmvp.listeners.MainActivityViewListener
import com.kai.kotlinmvp.model.Author
import com.kai.kotlinmvp.presenter.AuthorPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MainActivityViewListener {
    private val mAuthorPresenter = AuthorPresenter( this )
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAuthorViewModel: AuthorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = View.GONE
        mRecyclerView = findViewById( R.id.recyclerView )
        when( getResources().getConfiguration().orientation ){
            Configuration.ORIENTATION_LANDSCAPE->{
                mRecyclerView.layoutManager =
                    GridLayoutManager(applicationContext, 5, LinearLayoutManager.VERTICAL, false)
            }
            Configuration.ORIENTATION_PORTRAIT->{
                mRecyclerView.layoutManager =
                    GridLayoutManager(applicationContext, 2, LinearLayoutManager.VERTICAL, false)
            }
        }
        mRecyclerView.setHasFixedSize( true )
        mAuthorViewModel = ViewModelProviders.of( this ).get( AuthorViewModel::class.java )
    }

    override fun onResume() {
        super.onResume()
        if( mAuthorViewModel.mAuthorList.size  <= 0 ) {
            mAuthorPresenter.getData()
        }
        else {
            setRecyclerData( mAuthorViewModel.mAuthorList )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mAuthorPresenter.onDestroy()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun setAuthorData(authorList: MutableList<Author>) {
        mAuthorViewModel.mAuthorList = authorList
        setRecyclerData( mAuthorViewModel.mAuthorList )
    }

    override fun onItemClick(author: Author) {
        Toast.makeText( applicationContext, "You clicked ${author.authorName}", Toast.LENGTH_LONG ).show()
    }

    override fun showError() {
        Toast.makeText( applicationContext, "Could not load data", Toast.LENGTH_SHORT ).show()
    }

    private fun setRecyclerData( authorList: MutableList<Author> )
    {
        mRecyclerView.adapter = AuthorListAdapter(authorList) {
            mAuthorPresenter.onItemClick(it)
        }
    }
}
