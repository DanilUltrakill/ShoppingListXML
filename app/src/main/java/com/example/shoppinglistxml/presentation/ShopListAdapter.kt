package com.example.shoppinglistxml.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shoppinglistxml.R
import com.example.shoppinglistxml.domain.ShopItem
import androidx.recyclerview.widget.ListAdapter

class ShopListAdapter :
    ListAdapter<ShopItem, ShopItemViewHolder>(
        ShopItemDiffCallback()
    ) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            ENABLE_VIEWTYPE -> R.layout.item_shop_enabled
            DISABLE_VIEWTYPE -> R.layout.item_shop_disabled

            else -> throw RuntimeException("Unknown viewType: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()

        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }

        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {

        return if (getItem(position).enabled) {
            ENABLE_VIEWTYPE
        } else {
            DISABLE_VIEWTYPE
        }
    }

    companion object {
        const val ENABLE_VIEWTYPE = 0
        const val DISABLE_VIEWTYPE = 1

        const val MAX_POOL_SIZE = 15
    }
}