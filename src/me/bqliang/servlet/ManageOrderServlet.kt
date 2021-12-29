package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.OrderDao
import me.bqliang.dao.OrderItemDao

/**
 * 处理用户订单状态
 *
 */
@WebServlet(name = "ManageOrderServlet", value = ["/ManageOrderServlet"])
class ManageOrderServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 首先获取用户提交过来的信息
        val oid = req.getParameter("oid")
        val state = req.getParameter("state").toInt()

        // 从数据库中获取该订单信息
        val order = OrderDao.getOrderByOid(oid)
        order.orderItems = OrderItemDao.getOrderItemByOid(order.oid)
        req.setAttribute("order", order)

        /**
         * 根据状态码跳转到不同页面
         * -1 -> (待支付)  ->  删除未付款订单
         *  0 -> (待支付)  ->  支付
         *  1 -> (待发货)  ->  催单
         *  2 -> (已发货)  ->  确定收货
         *  3 -> (已收获)  ->  评价
         *  4 -> (已完成)  ->  查看订单信息
         */
        when(state){
            -1 ->{
                // 删除订单再跳转到“我的订单”页面
                OrderDao.deleteOrderByOid(oid)
                resp.sendRedirect("OrderListServlet?currentPage=1")
            }
            0 -> {
                // 跳转到付款页面
                req.getRequestDispatcher("account.jsp").forward(req, resp)
            }
            1 -> {
                // 催单
                resp.sendRedirect("OrderListServlet?currentPage=1")
            }
            2 -> {
                // 确定收货
                OrderDao.updateOrderState(order.oid, 3)
                resp.sendRedirect("OrderListServlet?currentPage=1")
            }
            // 已收货进入评价页面
            3 -> req.getRequestDispatcher("assess.jsp").forward(req, resp)
            else -> req.getRequestDispatcher("order.jsp").forward(req, resp)
        }
    }
}