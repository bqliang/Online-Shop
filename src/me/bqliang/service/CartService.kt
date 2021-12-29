package me.bqliang.service

import me.bqliang.dao.ProductDao
import me.bqliang.model.CartItem

object CartService {

    /**
     * 通过商品编号和数量构造购物车 item
     *
     * @param pid 商品编号
     * @param num 商品数量
     * @return 购物车 item
     */
    fun createCartItem(pid: String, num: Int) = CartItem().apply {
        product = ProductDao.findProductById(pid)
        buyNum = num
    }
}