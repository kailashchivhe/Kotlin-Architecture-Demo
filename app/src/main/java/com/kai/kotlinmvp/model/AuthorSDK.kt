package com.kai.kotlinmvp.model

import com.kai.kotlinmvp.helper.NetworkHelper
import org.json.JSONArray

class AuthorSDK
{
    fun getAuthorList(): MutableList<Author>
    {
        val list = mutableListOf<Author>()
        val jsonString = NetworkHelper.getJsonStringData( "https://picsum.photos/list" )
        if( jsonString.startsWith("Error") )
        {
            return list
        }
        val jsonArray = JSONArray( jsonString )
        for( i in 0 until jsonArray.length() )
        {
            val authorData = jsonArray.getJSONObject( i )
            val authorId = authorData.getInt("id")
            val authorName = authorData.getString( "author" )
            val author = Author(authorId, authorName)
            list.add( author )
        }
        return list
    }
}