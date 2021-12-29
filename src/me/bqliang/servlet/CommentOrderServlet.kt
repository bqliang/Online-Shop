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
        // 更新数据库给订单添加评论
        OrderDao.addComment(oid, assess)
        // 返回“我的订单”页面
        resp.sendRedirect("OrderListServlet?currentPage=1")
    }
}