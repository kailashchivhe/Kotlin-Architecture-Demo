package com.kai.kotlinmvp.listeners

import com.kai.kotlinmvp.model.Author

interface AuthorDataListener {
    fun onResultSuccess( authorList: MutableList<Author> )
    fun onResultFail( error: String )
}