package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.OrderDao

/**
 * 评论
 *
 */
@WebServlet(name = "CommentOrderServlet", value = ["/CommentOrderServlet"])
class CommentOrderServlet: HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 获取用户提交的订单编号和评论
        req.characterEncoding = "UTF-8"
        val oid = req.getParameter("oid")
        val assess = req.getParameter("assess")
        // 更新数据库添加评论
        OrderDao.addComment(oid, assess)
        // 更新订单状态
        OrderDao.updateOrderState(oid, 4)
        // 通过 ManageOrderServlet 跳转到订单详情页面
        resp.sendRedirect("OrderListServlet?currentPage=1")
    }
}