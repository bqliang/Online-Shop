package me.bqliang.service

import me.bqliang.model.Cart
import me.bqliang.model.Order
import me.bqliang.model.OrderItem
import me.bqliang.model.User
import java.time.LocalDateTime
import java.util.*

object OrderService {

    /**
     * 根据用户和购物车信息构造订单
     *
     * @param user 用户
     * @param cart 购物车
     * @return 订单
     */
    fun createOrder(user: User, cart: Cart): Order {

        // 产生订单信息
        val order = Order().apply {
            oid = UUID.randomUUID().toString()
            name = user.name
            uid = user.uid
            telephone = user.telephone
            address = user.address
            orderItems = mutableListOf<OrderItem>()
        }

        // 从购物车中获取商品信息来构建 Order item 并添加到订单中
        cart.cartItems.forEach {
            val orderItem = OrderItem().apply {
                itemId = UUID.randomUUID().toString()
                count = it.value.buyNum
                product = it.value.product
                oid = order.oid
            }
            order.orderItems.add(orderItem)
        }

        return order
    }
}