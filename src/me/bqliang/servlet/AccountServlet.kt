package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.OrderDao
import me.bqliang.model.User

/**
 * 订单支付
 *
 */
@WebServlet(name = "AccountServlet", value = ["/AccountServlet"])
class AccountServlet: HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 获取用户提交的订单编号和支付密码
        val oid = req.getParameter("oid")
        val password = req.getParameter("password")
        // 校验密码
        val user = req.session.getAttribute("user") as User
        if (user.password == password){
            // 密码正确，将订单状态设置为支付成功，并跳转到“我的订单”页面
            OrderDao.updateOrderState(oid, 1)
            resp.sendRedirect("OrderListServlet?currentPage=1")
        }else{
            req.apply {
                setAttribute("error", "密码错误，请重新输入")
                getRequestDispatcher("account.jsp").forward(req, resp)
            }
        }
    }
}