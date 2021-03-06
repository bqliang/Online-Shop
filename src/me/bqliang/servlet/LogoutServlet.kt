package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * 处理用户注销请求
 *
 */
@WebServlet(name = "LogoutServlet", value = ["/LogoutServlet"])
class LogoutServlet: HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 清空 session 作用域中的 user 对象，然后重定向到登录页面
        req.session.removeAttribute("user")
        resp.sendRedirect("login.jsp")
    }
}