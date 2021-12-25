package me.bqliang.dao

import me.bqliang.model.Order
import me.bqliang.model.OrderItem
import me.bqliang.utils.myQR
import org.apache.commons.dbutils.handlers.BeanHandler
import org.apache.commons.dbutils.handlers.BeanListHandler

/**
 * 订单数据库操作
 */
object OrderDao {

    /**
     * 添加订单
     *
     * @param order 订单数据
     */
     fun addOrder(order: Order){
        myQR.update(
            "INSERT INTO orders VALUES (?,now(),?,?,?,?,?,?,NULL)",
            order.oid,
            order.total,
            order.state,
            order.address,
            order.name,
            order.telephone,
            order.uid,
        )
        OrderItemDao.addOrderItems(order.orderItems)
     }


    /**
     * 根据用户编号来获取订单信息
     *
     * @param uid 用户编号
     */
    fun getOrderByUid(uid: String) = myQR.query(
        "SELECT * FROM orders WHERE uid = ?",
        BeanListHandler(Order::class.java),
        uid
    )


    /**
     * 根据订单编号来获取订单信息
     *
     * @param oid 订单编号
     */
    fun getOrderByOid(oid: String) = myQR.query(
        "SELECT * FROM orders WHERE oid = ?",
        BeanHandler(Order::class.java),
        oid
    )


    /**
     * 修改订单状态
     * @param oid 订单编号
     * @param state 订单状态
     *
     */
    fun updateOrderState(oid: String, state: Int) = myQR.update(
        "UPDATE orders SET state = ? WHERE oid = ?",
        state,
        oid
    )

    /**
     * 删除订单
     *
     * @param oid 订单编号
     * @return 影响行数
     */
    fun deleteOrderByOid(oid: String): Int {
        // 删除订单前先删除订单 item
        OrderItemDao.deleteOrderItemByOid(oid)
        return myQR.update("DELETE FROM orders WHERE oid = ?", oid)
    }


    /**
     * 更新订单评价
     *
     * @param oid 订单编号
     * @param comment 评论
     * @return 影响行数
     */
    fun addComment(oid: String, comment: String) = myQR.update(
        "UPDATE orders SET assess = ? WHERE oid = ?",
        comment,
        oid
    )
}