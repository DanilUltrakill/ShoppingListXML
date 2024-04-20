package com.example.shoppinglistxml.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shoppinglistxml.R
import com.example.shoppinglistxml.domain.ShopItem
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglistxml.databinding.ItemShopDisabledBinding

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

        val binding = ItemShopDisabledBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding
        binding.tvName.text = shopItem.name
        binding.tvCount.text = shopItem.count.toString()

        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }

        binding.root.setOnClickListener {
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