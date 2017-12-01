package sg.vinova.besttrip.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.util.*

/**
 * Created by Hanah on 11/22/2017.
 */
open class BaseBAdapter<DATA, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    private var list = ArrayList<DATA>()
    var lastPosition = -1

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH? {
        return null
    }

    fun add(data: DATA?) {
        if (data == null)
            return
        list.add(data)
        notifyItemInserted(list.size)
    }

    fun add(data: DATA?, position: Int) {
        if (data == null)
            return
        if (position < 0)
            return
        list.add(position, data)
        notifyItemInserted(list.size)
    }

    fun addAll(list: ArrayList<DATA>) {
        if (list.size == 0)
            return
        val pos = itemCount
        this.list.addAll(list)
        notifyItemRangeChanged(pos, this.list.size)
    }

    fun remove(data: DATA) {
        if (list.size == 0)
            return
        list.remove(data)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        if (list.size == 0)
            return
        list.removeAt(position)
        notifyDataSetChanged()
    }

    fun clear() {
        if (list.size == 0)
            return
        list.clear()
        notifyDataSetChanged()
    }

    open fun getItemAt(position: Int): DATA {
        var d: DATA? = null
        if (position in 0..(itemCount - 1)) {
            d = list[position]
        }
        return d!!
    }
}
