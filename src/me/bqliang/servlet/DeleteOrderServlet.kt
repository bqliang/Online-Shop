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
        val oid = req.getParameter("oid")
        OrderDao.deleteOrderByOid(oid)
        resp.sendRedirect("OrderListServlet?currentPage=1")
    }
}