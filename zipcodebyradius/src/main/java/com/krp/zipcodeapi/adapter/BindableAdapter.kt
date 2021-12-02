package com.krp.zipcodeapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.krp.zipcodeapi.zipcodebyradius.BR

/**
 * Created by Rakesh Praneeth.
 *
 * It's a recyclerview adapter that uses data binding concept to take any list of item and display
 * in the recyclerview. The item layout variable should be "item".
 */
abstract class BindableAdapter : RecyclerView.Adapter<BindableAdapter.BindingViewHolder?>() {

    /**
     * Creates view holder, depending on the view type provided.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return BindingViewHolder(binding)
    }

    /**
     * binding the view holder of the particular position,
     */
    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val obj = getObjForPosition(position)
        holder.bind(obj)
    }

    /**
     * Gets the view type which is the layout id of the item at the given position.
     */
    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    /**
     * Object for the given position.
     */
    protected abstract fun getObjForPosition(position: Int): Any

    /**
     * Id of the layout for the position.
     */
    protected abstract fun getLayoutIdForPosition(position: Int): Int

    /**
     * ViewHolder for the adapter.
     */
    class BindingViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: Any?) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}
