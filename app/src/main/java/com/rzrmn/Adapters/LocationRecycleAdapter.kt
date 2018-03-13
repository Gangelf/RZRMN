package com.rzrmn.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rzrmn.Model.Location
import com.rzrmn.R
import kotlinx.android.synthetic.main.location_list_item.view.*

/**
 * Created by Gangelf on 3/6/2018.
 */
class LocationRecycleAdapter(val context: Context, val locations: List<Location>) : RecyclerView.Adapter<LocationRecycleAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
       return locations.count()
    }

    override fun onBindViewHolder(Holder: Holder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val locationImage = itemView?.findViewById<ImageView>(R.id.locationImage)
        val locationName = itemView?.findViewById<TextView>(R.id.locationName)
    }
}