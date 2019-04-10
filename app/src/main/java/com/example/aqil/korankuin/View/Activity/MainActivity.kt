package com.example.aqil.korankuin.View.Activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.Fade
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import com.example.aqil.korankuin.Model.Article
import com.example.aqil.korankuin.R
import com.example.aqil.korankuin.View.ArticleAdapter
import com.example.aqil.korankuin.View.CarouselListener
import com.example.aqil.korankuin.ViewModel.ApiViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var adapter: ArticleAdapter
    lateinit var carouselListener: CarouselListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar_cyclic.visibility = View.VISIBLE
        supportActionBar?.setCustomView(R.layout.app_bar_main)
        initRecyclerView()
        showData()
        setupWindowAnimations()
        overridePendingTransition(0, 0)

    }

    private fun initRecyclerView() {

        adapter = ArticleAdapter(this, this)
        rv_articles.layoutManager = LinearLayoutManager(this)
        rv_articles.adapter = adapter

    }

    private fun showData() {
        val apiRequetsViewModel = ViewModelProviders.of(this).get(ApiViewModel::class.java)
        Log.d("TAGI", "TAGI")
        apiRequetsViewModel.getTopArticles().observe(this, Observer<ArrayList<Article>> { t ->
            Log.d("TAGII", t?.get(0)?.content)
            t?.let {
                adapter.setListArticle(it)
                carouselListener = CarouselListener(this, it, this)
                carouselView.setViewListener(carouselListener)
                carouselView.setImageClickListener(carouselListener)
                carouselView.pageCount = 5
            }
        })


    }

    private fun setupWindowAnimations() {
        var fade = Fade()
        fade.duration=100
        window.exitTransition = fade
        window.allowEnterTransitionOverlap = false
        window.allowReturnTransitionOverlap = false

    }

}

