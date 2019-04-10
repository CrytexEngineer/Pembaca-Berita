package com.example.aqil.korankuin

import com.example.aqil.korankuin.Model.ArticleList
import retrofit2.Call
import retrofit2.http.GET

interface ArticleDataService {
    @GET("top-headlines?country=us&apiKey=" + BuildConfig.API_KEY)
    fun getTopArticles(): Call<ArticleList>
}