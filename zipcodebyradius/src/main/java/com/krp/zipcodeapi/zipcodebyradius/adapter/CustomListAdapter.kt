package com.krp.zipcodeapi.zipcodebyradius.adapter

import com.krp.zipcodeapi.adapter.BindableAdapter
import com.krp.zipcodeapi.adapter.ListItem

/**
 * Created by Rakesh Praneeth.
 */
class CustomListAdapter : BindableAdapter() {

    /**
     * Mutable list of items to display in the adapter.
     */
    private var list: MutableList<ListItem> = mutableListOf()

    /**
     * Clears the old data and set the new data provided and refresh the recyclerview.
     * @param newList list of items to display on the UI.
     */
    fun setList(newList: List<ListItem>?) {
        if (list.isNotEmpty()) {
            list.clear()
        }
        list.addAll(newList!!)
        notifyDataSetChanged()
    }

    /**
     * Gives the list item for the position from the [list].
     * @param position of the item being visible.
     */
    override fun getObjForPosition(position: Int): Any {
        return list[position]
    }

    /**
     * Gets the layout id for the position of item.
     * @param position of the item being visible.
     */
    override fun getLayoutIdForPosition(position: Int): Int {
        return list[position].layoutId
    }

    /**
     * Number of items provided.
     */
    override fun getItemCount(): Int {
        return list.size
    }
}
