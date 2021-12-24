package me.bqliang.dao

import me.bqliang.model.Order
import me.bqliang.utils.myQR

/**
 * 订单数据库操作
 */
object OrderDao {

    /**
     * 添加订单
     *
     * @param order 订单数据
     * @return 影响行数
     */
     fun addOrder(order: Order) = myQR.update(
         "INSERT INTO orders VALUES (?,?,?,?,?,?,?,?,?)",
         order.oid,
         order.orderTime,
         order.total,
         order.state,
         order.address,
         order.name,
         order.telephone,
         order.uid,
         order.assess
     )
}