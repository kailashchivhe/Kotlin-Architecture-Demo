package com.kai.kotlinmvp.view

import androidx.lifecycle.ViewModel
import com.kai.kotlinmvp.model.Author

class AuthorViewModel : ViewModel(){
    var mAuthorList: MutableList<Author> = mutableListOf()
}