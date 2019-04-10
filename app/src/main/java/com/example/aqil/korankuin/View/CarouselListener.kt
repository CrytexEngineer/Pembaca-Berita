package com.example.aqil.korankuin.View

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.aqil.korankuin.Model.Article
import com.example.aqil.korankuin.R
import com.example.aqil.korankuin.View.Activity.DetailActivity
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageClickListener
import com.synnapps.carouselview.ViewListener
import org.jetbrains.anko.find

class CarouselListener(private var context: Context, private var imgList: ArrayList<Article>, private var activity: Activity) : ViewListener, ImageClickListener {


    override fun onClick(position: Int) {

        val i = Intent(activity, DetailActivity::class.java)
        i.putExtra(context.getString(R.string.ARTICLE_DETAIL), imgList.get(position))
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity)
        imgList.get(position).let {
            activity.startActivity(i, transitionActivityOptions.toBundle())
        }

    }

    override fun setViewForPosition(position: Int): View {
        val customView: View = LayoutInflater.from(context).inflate(R.layout.carousel_item_article, null)
        Picasso.get().load(imgList[position].urlToImage).fit().centerCrop().into(customView.find<ImageView>(R.id.img_item_carousel))
        customView.findViewById<TextView>(R.id.tv_title_carousel).text = imgList[position].title
        return customView
    }


}