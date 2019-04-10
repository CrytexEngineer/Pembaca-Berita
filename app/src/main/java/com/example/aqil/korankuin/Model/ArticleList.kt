package com.example.aqil.korankuin.Model

import com.google.gson.annotations.SerializedName

class ArticleList {
    @SerializedName("articles")
    lateinit var articleList: ArrayList<Article>

}