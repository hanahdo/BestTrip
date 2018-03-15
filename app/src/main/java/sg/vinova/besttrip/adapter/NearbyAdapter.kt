package sg.vinova.besttrip.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_search_places.view.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBAdapter
import sg.vinova.besttrip.model.places.Location
import sg.vinova.besttrip.model.places.Result
import sg.vinova.besttrip.services.BaseListener

/**
 * Created by hanah on 12/1/17.
 */
class NearbyAdapter : BaseBAdapter<Result, NearbyAdapter.SearchVH>() {
    var listener: BaseListener.OnItemClickListener<Location>? = null
    lateinit var context: Context

    override fun getViewHolder(parent: ViewGroup, viewType: Int): SearchVH {
        context = parent.context
        return SearchVH(LayoutInflater.from(context).inflate(R.layout.item_search_places, parent, false))
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(position)
    }

    inner class SearchVH(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val result: Result = getItemAt(position)

            itemView.tvAddress.text = context.resources.getString(R.string.result, result.name, result.address2)

            if (position == itemCount - 1) itemView.vLine.visibility = View.INVISIBLE

            itemView.setOnClickListener({
                if (listener != null)
                    listener!!.onItemClick(result.geometry!!.location!!)
            })
        }
    }
}
