package com.kai.kotlinmvp.listeners

import com.kai.kotlinmvp.model.Author

interface MainActivityListeners {
    interface MainActivityViewListeners{
        fun showProgress()
        fun hideProgress()
        fun setAuthorData(authorList: MutableList<Author>)
        fun showError()
        fun onItemClick(author: Author)
    }
    interface AuthorDataListener {
        fun onResultReceived(authorList: MutableList<Author> )
    }
}