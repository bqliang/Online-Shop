package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.model.Cart
import me.bqliang.model.Order
import me.bqliang.model.OrderItem
import me.bqliang.model.User
import me.bqliang.service.OrderService
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
        val cart = req.session.getAttribute("cart") as Cart
        val order = OrderService.createOrder(user, cart)

        // 设置数据并跳转到订单确认界面
        req.session.setAttribute("order", order)
        resp.sendRedirect("order_info.jsp")
    }
}