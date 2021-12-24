package me.bqliang.dao

import me.bqliang.model.OrderItem
import me.bqliang.utils.myQR

/**
 * 订单 item 数据库操作
 */
object OrderItemDao {

    /**
     * 添加购物车 item
     *
     * @param orderItem 购物车 item
     * @return 影响行数
     */
    fun addOrderItem(orderItem: OrderItem) = myQR.update(
        "INSERT INTO orderitem VALUES (?,?,?,?,?)",
        orderItem.itemId,
        orderItem.count,
        orderItem.subtotal,
        orderItem.product.pid,
        orderItem.oid
    )
}