package com.kai.kotlinmvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kai.kotlinmvp.R
import com.kai.kotlinmvp.listeners.MainActivityViewListener
import com.kai.kotlinmvp.model.Author
import com.kai.kotlinmvp.presenter.AuthorPresenter

class MainActivity : AppCompatActivity(),MainActivityViewListener {

    private lateinit var mAuthorPresenter: AuthorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuthorPresenter = AuthorPresenter( this )
    }

    override fun onResume() {
        mAuthorPresenter.getData()
        super.onResume()
    }

    override fun onDestroy() {
        mAuthorPresenter.onDestroy()
        super.onDestroy()
    }
    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAuthorData(authorList: MutableList<Author>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
