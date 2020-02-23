package com.kai.kotlinmvp.listeners

import com.kai.kotlinmvp.model.Author

interface AuthorDataListener {
    fun onResultReceived(authorList: MutableList<Author> )
}