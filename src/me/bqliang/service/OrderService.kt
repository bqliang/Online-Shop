package me.bqliang.service

import me.bqliang.model.Cart
import me.bqliang.model.Order
import me.bqliang.model.User
import java.util.*

object OrderService {

    /**
     * 根据用户和购物车信息
     *
     * @param user 用户
     * @param cart 购物车
     * @return
     */
    fun createOrder(user: User, cart: Cart) = Order().apply {
        oid = UUID.randomUUID().toString()
        address = user.address
        name = user.name
        orderTime = Date()
        state = 0
        telephone = user.telephone
        uid = user.uid
        //orderItems =
    }

}