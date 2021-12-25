package me.bqliang.dao

import me.bqliang.model.OrderItem
import me.bqliang.model.Product
import me.bqliang.utils.myQR
import org.apache.commons.dbutils.handlers.BeanListHandler

/**
 * 订单 item 数据库操作
 */
object OrderItemDao {

    /**
     * 添加多条Order item
     *
     * @param orderItem List<OrderItem>
     */
    fun addOrderItems(orderItems: List<OrderItem>){
        for (orderItem in orderItems){
            myQR.update(
                "INSERT INTO orderitem VALUES (?,?,?,?,?)",
                orderItem.itemId,
                orderItem.count,
                orderItem.subtotal,
                orderItem.product.pid,
                orderItem.oid
            )
        }
    }


    /**
     * 根据订单 ID 查询 Order item
     *
     * @param oid 订单编号
     * @return List<OrderItem>
     */
    fun getOrderItemByOid(oid: String): List<OrderItem> {
        val sql = "SELECT * FROM orderitem WHERE oid = ?"

        val orderItems = myQR.query(
            sql,
            BeanListHandler(OrderItem::class.java),
            oid
        )

        val pidList = myQR.query(
            sql,
            BeanListHandler(Product::class.java),
            oid
        )

        return orderItems.mapIndexed { index, orderItem ->
            orderItem.apply { product = ProductDao.findProductById(pidList[index].pid) }
        }
    }


    /**
     * 根据订单编号删除订单 item
     *
     * @param oid 订单编号
     */
    fun deleteOrderItemByOid(oid: String) = myQR.update("DELETE FROM orderitem where oid = ?", oid)

}