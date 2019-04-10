package com.example.aqil.korankuin.View

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.aqil.korankuin.Model.Article
import com.example.aqil.korankuin.R
import com.example.aqil.korankuin.View.Activity.DetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import java.util.*

class ArticleAdapter(private var context: Context, private var activity: Activity) : RecyclerView.Adapter<ArticleAdapter.ArticleAdapater>() {

    class ArticleAdapater(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {


        fun bindItem(article: Article, listener: (Article) -> Unit) {

            Picasso.get().load(article.urlToImage).into(containerView.findViewById<ImageView>(R.id.article_item_thumbnail))
            containerView.findViewById<TextView>(R.id.article_item_title).text = article.title
            val dateTime: String = article.publishedAt?.substring(0, 10) + " I " + article.publishedAt?.substring(11, 16)
            containerView.findViewById<TextView>(R.id.article_item_date).text = dateTime
            containerView.setOnClickListener { listener(article) }

        }
    }

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mListArticles: List<Article>? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ArticleAdapater {
        val itemView: View = mInflater.inflate(R.layout.card_item_article, p0, false)
        return ArticleAdapater(itemView)
    }


    override fun getItemCount(): Int {
        return mListArticles?.size ?: 0
    }

    override fun onBindViewHolder(p0: ArticleAdapater, p1: Int) {
        val i = Intent(activity, DetailActivity::class.java)
        i.putExtra(context.getString(R.string.ARTICLE_DETAIL), mListArticles?.get(p1))
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity)
        mListArticles?.get(p1)?.let {
            p0.bindItem(it) { article: Article -> activity.startActivity(i, transitionActivityOptions.toBundle()) }


        }
    }

    fun setListArticle(mListClubItem: ArrayList<Article>) {
        this.mListArticles = mListClubItem
        notifyDataSetChanged()

    }
}

