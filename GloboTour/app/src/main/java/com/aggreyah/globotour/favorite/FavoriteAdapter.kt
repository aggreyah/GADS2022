package com.aggreyah.globotour.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.aggreyah.globotour.R
import com.aggreyah.globotour.city.City

class FavoriteAdapter(val context: Context, var favoriteCityList: MutableList<City>):
    RecyclerView.Adapter<FavoriteAdapter.FavoriteCityViewHolder>() {

    inner class FavoriteCityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var currentPosition = -1
        private var currentFavoriteCity: City? = null

        private val txvFavoriteCityName = itemView.findViewById<TextView>(R.id.txv_favorite_city_name)
        private val imvFavoriteCityImage = itemView.findViewById<ImageView>(R.id.imv_favorite_city)


        fun setData(favoriteCity: City, position: Int) {
            txvFavoriteCityName.text = favoriteCity.name
            imvFavoriteCityImage.setImageResource(favoriteCity.imageId)

            this.currentPosition = position
            this.currentFavoriteCity = favoriteCity
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCityViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_favorite_city, parent, false)
        return FavoriteCityViewHolder(itemView)
    }

    override fun onBindViewHolder(favoriteCityViewHolder: FavoriteCityViewHolder, position: Int) {
        val favoriteCity = favoriteCityList[position]
        favoriteCityViewHolder.setData(favoriteCity, position)
    }

    override fun getItemCount(): Int = favoriteCityList.size
}