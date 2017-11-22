package sg.vinova.besttrip.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sg.vinova.besttrip.R
import java.util.*

/**
 * Created by Hanah on 11/22/2017.
 */
abstract class BaseBAdapter<DATA>(private var context: Context) : RecyclerView.Adapter<BaseViewHolder>() {
    private var list = ArrayList<DATA>()
    var lastPosition = -1

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        val view: View = LayoutInflater.from(context).inflate(
                if (getLayoutId() == 0) R.layout.fragment_blank else getLayoutId(),
                parent,
                false)
        return BaseViewHolder(view)
    }

    abstract fun getLayoutId(): Int

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

    fun getItemAt(position: Int): DATA {
        var d: DATA? = null
        if (position in 0..(itemCount - 1)) {
            d = list[position]
        }
        return d!!
    }
}