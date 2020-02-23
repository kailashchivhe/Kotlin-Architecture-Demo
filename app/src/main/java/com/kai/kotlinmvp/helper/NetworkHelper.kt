package com.kai.kotlinmvp.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class NetworkHelper
{
    companion object
    {
        fun getJsonStringData( urlString : String): String
        {
            val url = URL( urlString )
            val connection = connect(url)
            if (connection.toString().startsWith("Error"))
            {
                return connection.toString()
            }
            try
            {
                val con = connection as HttpURLConnection
                if (con.responseCode == 200)
                {
                    val inputStream = BufferedInputStream(con.inputStream)
                    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                    val jsonData = StringBuffer()
                    var line: String?

                    do
                    {
                        line = bufferedReader.readLine()
                        if (line == null) {
                            break
                        }
                        jsonData.append(line + "n")
                    } while (true)

                    bufferedReader.close()
                    inputStream.close()
                    return jsonData.toString()
                }
                else
                {
                    return "Error " + con.responseMessage
                }
            }
            catch (e: IOException)
            {
                e.printStackTrace()
                return "Error " + e.message
            }
        }

        fun connect(url: URL): Any
        {
            try
            {
                val con = url.openConnection() as HttpURLConnection
                con.requestMethod = "GET"
                con.connectTimeout = 15000
                con.readTimeout = 15000
                con.doInput = true
                return con
            }
            catch (e: MalformedURLException)
            {
                e.printStackTrace()
                return "Error URL " + e.message
            }
            catch (e: IOException)
            {
                e.printStackTrace()
                return "Error CONNECT " + e.message
            }
        }

        fun getBitMap( urlString: String ): Bitmap
        {
            val url = URL( urlString )
            val bitmap = BitmapFactory.decodeStream( url.openConnection().getInputStream() )
            return bitmap
        }
    }
}