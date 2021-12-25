package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.model.Cart
import me.bqliang.model.Order
import me.bqliang.model.OrderItem
import me.bqliang.model.User
import java.util.*

/**
 * 处理结算购物车
 *
 */
@WebServlet(name = "SubmitOrderServlet", value = ["/SubmitOrderServlet"])
class SubmitOrderServlet : HttpServlet(){

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {

        // 先判断用户是否登录，如果没有登录则跳转到登录页面
        val user = req.session.getAttribute("user") as User?
        if (user == null){
            resp.sendRedirect("login.jsp")
            return
        }

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
        val cart = req.session.getAttribute("cart") as Cart
        cart.cartItems.forEach {
            val orderItem = OrderItem().apply {
                itemId = UUID.randomUUID().toString()
                count = it.value.buyNum
                product = it.value.product
                oid = order.oid
            }
            order.orderItems.add(orderItem)
        }

        // 设置数据并跳转到订单确认界面
        req.session.setAttribute("order", order)
        resp.sendRedirect("order_info.jsp")
    }
}