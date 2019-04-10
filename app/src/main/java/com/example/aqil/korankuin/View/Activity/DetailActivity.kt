package com.example.aqil.korankuin.View.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import com.example.aqil.korankuin.Model.Article
import com.example.aqil.korankuin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setCustomView(R.layout.app_bar_main)
        bindData()
        setupWindowAnimations()

    }

    private fun bindData() {
        val article: Article = intent.getParcelableExtra(getString(R.string.ARTICLE_DETAIL))
        Picasso.get().load(article.urlToImage).centerCrop().fit().into(img_article)
        Log.d("TAG", article.urlToImage)
        article_title.text = article.title
        article_content.text = article.content
        article_author.text = article.author
        article_source.text = article.source?.name.toString()
        val dateTime: String = article.publishedAt?.substring(0, 10) + " I " + article.publishedAt?.substring(11, 16)
        article_date.text = dateTime
    }

    private fun setupWindowAnimations() {
        val slideTransition = Slide()
        slideTransition.slideEdge = Gravity.RIGHT
        slideTransition.duration = 250
        window.enterTransition = slideTransition
        window.allowEnterTransitionOverlap = false
        window.allowReturnTransitionOverlap = false


    }

}
