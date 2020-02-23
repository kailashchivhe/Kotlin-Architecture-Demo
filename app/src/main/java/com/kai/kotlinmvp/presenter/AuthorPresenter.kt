package com.kai.kotlinmvp.presenter

import com.kai.kotlinmvp.listeners.AuthorDataListener
import com.kai.kotlinmvp.listeners.MainActivityViewListener
import com.kai.kotlinmvp.model.Author
import com.kai.kotlinmvp.model.AuthorSDK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorPresenter( private var mainActivityViewListener: MainActivityViewListener? ) : AuthorDataListener
{
    fun getData()
    {
        mainActivityViewListener?.showProgress()
        CoroutineScope(IO).launch {
            val authorList = AuthorSDK().getAuthorList()
            setListOnMainThread(authorList)
        }
    }

    suspend fun setListOnMainThread( authorList: MutableList<Author> )
    {
        withContext(Main){
            onResultReceived( authorList )
        }
    }

    fun onDestroy()
    {
        mainActivityViewListener = null
    }

    override fun onResultReceived(authorList: MutableList<Author>)
    {
        mainActivityViewListener?.hideProgress()
        if( authorList.size > 0 ) {
            mainActivityViewListener?.setAuthorData(authorList)
        }
        else{
            mainActivityViewListener?.showError()
        }
    }

    fun onItemClick(author: Author) {
        mainActivityViewListener?.onItemClick( author )
    }
}