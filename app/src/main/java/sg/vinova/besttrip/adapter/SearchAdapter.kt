package sg.vinova.besttrip.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sg.vinova.besttrip.R
import kotlinx.android.synthetic.main.item_search_places.view.*
import sg.vinova.besttrip.base.BaseBAdapter
import sg.vinova.besttrip.model.search.Result

/**
 * Created by hanah on 12/1/17.
 */
class SearchAdapter : BaseBAdapter<Result, SearchAdapter.SearchVH>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchVH? {
        context = parent!!.context
        return SearchVH(LayoutInflater.from(context).inflate(R.layout.item_search_places, parent, false))
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(position)
    }

    inner class SearchVH(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val result: Result = getItemAt(position)
            itemView.tvName.text = result.name
            itemView.tvAddress.text = result.formattedAddress
        }
    }
}
