package com.krp.zipcodeapi.zipcodebyradius.adapter

import android.content.Context
import com.krp.zipcodeapi.adapter.ListItem
import com.krp.zipcodeapi.zipcodebyradius.R
import com.krp.zipcodeapi.zipcodebyradius.model.Zipcode

/**
 * Created by Rakesh Praneeth.
 */
class ZipcodeListItem(val zipcode: Zipcode) : ListItem() {
    override val layoutId: Int = R.layout.item_zipcode

    fun getCity(context: Context) = context.getString(R.string.item_city_format, zipcode.city)

    fun getState(context: Context) = context.getString(R.string.item_state_format, zipcode.state)

    fun getZipcode(context: Context) = context.getString(R.string.item_zipcode_format, zipcode.zipcode)

    fun getDistance(context: Context) = context.getString(R.string.item_distance_format, zipcode.distance)
}