package com.kai.kotlinmvp.listeners

import com.kai.kotlinmvp.model.Author

interface MainActivityViewListener {
    fun showProgress()
    fun hideProgress()
    fun setAuthorData( authorList: MutableList<Author> )
    fun showError()
    fun onItemClick(author: Author)
}