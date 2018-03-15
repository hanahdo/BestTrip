package sg.vinova.besttrip.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_search_places.view.*
import sg.vinova.besttrip.R
import sg.vinova.besttrip.base.BaseBAdapter
import sg.vinova.besttrip.model.autocomplete.Prediction
import sg.vinova.besttrip.services.BaseListener

/**
 * Created by Hanah on 12/7/2017.
 */
class SearchAdapter : BaseBAdapter<Prediction, SearchAdapter.SearchVH>() {
    var listener: BaseListener.OnItemClickListener<String>? = null
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
            val prediction: Prediction = getItemAt(position)

            itemView.tvAddress.text = prediction.description

            if (position == itemCount - 1) itemView.vLine.visibility = View.INVISIBLE

            itemView.setOnClickListener({
                if (listener != null)
                    listener!!.onItemClick(prediction.placeId)
            })
        }
    }
}