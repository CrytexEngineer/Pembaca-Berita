package com.example.aqil.korankuin.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.aqil.korankuin.Model.Article
import com.example.aqil.korankuin.Model.MasterRepository

class ApiViewModel(application: Application) : AndroidViewModel(application) {
    private val masterRepository = MasterRepository()

    fun getTopArticles(): LiveData<ArrayList<Article>> {
        return masterRepository.getTopArticles()
    }


}