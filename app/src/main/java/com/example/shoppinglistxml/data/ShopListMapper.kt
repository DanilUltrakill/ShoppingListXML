package com.example.shoppinglistxml.data

import com.example.shoppinglistxml.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        shopItem.id,
        shopItem.name,
        shopItem.count,
        shopItem.enabled
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        shopItemDbModel.name,
        shopItemDbModel.count,
        shopItemDbModel.enabled,
        shopItemDbModel.id,
    )

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}