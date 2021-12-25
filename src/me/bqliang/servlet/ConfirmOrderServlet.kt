package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.OrderDao
import me.bqliang.model.Order

/**
 * 确定订单
 *
 */
@WebServlet(name = "ConfirmOrderServlet", value = ["/ConfirmOrderServlet"])
class ConfirmOrderServlet: HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {

        // 获取订单并设置收件人、手机、收件地址和订单状态，然后保存到数据库
        val order = req.session.getAttribute("order") as Order
        order.apply {
            name = req.getParameter("name")
            telephone = req.getParameter("telephone")
            address = req.getParameter("address")
            state = 0
        }.let(OrderDao::addOrder)

        // 将购物车清空并设置新的 Order 信息
        req.session.apply {
            removeAttribute("cart")
            setAttribute("order", order)
        }
        // 重定向到付款页面
        resp.sendRedirect("account.jsp")
    }
}