package com.krp.zipcodeapi.adapter

/**
 * Created by Rakesh Praneeth.
 * Base class that needs to be extended by the other items to display in the
 * Bindable adapter.
 * class extending it need to provide the layoutId.
 */
abstract class ListItem {
    abstract val layoutId: Int
}