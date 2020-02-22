package com.kai.kotlinmvp.presenter

import com.kai.kotlinmvp.listeners.AuthorDataListener
import com.kai.kotlinmvp.listeners.MainActivityViewListener
import com.kai.kotlinmvp.model.Author
import com.kai.kotlinmvp.model.AuthorSDK

class AuthorPresenter( private var mainActivityViewListener: MainActivityViewListener? ) : AuthorDataListener
{
    fun getData()
    {
        mainActivityViewListener?.showProgress()
        AuthorSDK().getData(this )
    }

    fun onDestroy()
    {
        mainActivityViewListener = null
    }

    override fun onResultSuccess(authorList: MutableList<Author>)
    {
        mainActivityViewListener?.hideProgress()
        mainActivityViewListener?.setAuthorData( authorList )
    }

    override fun onResultFail(error: String)
    {
        mainActivityViewListener?.hideProgress()
        mainActivityViewListener?.showError( error )
    }
}