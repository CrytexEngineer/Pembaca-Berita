package com.example.aqil.korankuin.Model

import RetrofitInstance
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.aqil.korankuin.ArticleDataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MasterRepository {

    //Inisiasi Variable
    private val mArticles: MutableLiveData<ArrayList<Article>> by lazy {
        MutableLiveData<ArrayList<Article>>()
    }

    //Membuat Retrofit Instance
    private val articleDataService = RetrofitInstance.getRetrofitInstance().create(ArticleDataService::class.java)

    //Mengambil Data Dari API
    fun getTopArticles(): MutableLiveData<ArrayList<Article>> {

        val call: Call<ArticleList> = articleDataService.getTopArticles()
        val obj = object : Callback<ArticleList> {
            override fun onResponse(call: Call<ArticleList>?, response: Response<ArticleList>?) {
                mArticles.value = response?.body()?.articleList
            }

            override fun onFailure(call: Call<ArticleList>?, t: Throwable?) {
                Log.d("EROR", t.toString())
            }
        }
        call.enqueue(obj)
        return mArticles
    }
}