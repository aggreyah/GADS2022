package com.aggreyah.globotour.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aggreyah.globotour.R
import com.aggreyah.globotour.city.VacationSpots


class FavoriteFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        setUpRecyclerView(view)
        return view
    }

    private fun setUpRecyclerView(view: View?) {
        val context = requireContext()
        val favoriteAdapter = FavoriteAdapter(context, VacationSpots.favoriteCityList)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.favorite_recycler_view)
        recyclerView?.adapter = favoriteAdapter
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager

    }
}