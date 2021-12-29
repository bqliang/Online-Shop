package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.OrderDao

/**
 * 删除订单
 *
 */
@WebServlet(name = "DeleteOrderServlet", value = ["/DeleteOrderServlet"])
class DeleteOrderServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 获取用户要删除的订单的编号
        val oid = req.getParameter("oid")
        // 从数据库中删除这个订单
        OrderDao.deleteOrderByOid(oid)
        // 重定向回“我的订单”页面
        resp.sendRedirect("OrderListServlet?currentPage=1")
    }
}