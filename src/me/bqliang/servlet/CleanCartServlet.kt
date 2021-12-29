package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse


/**
 * 处理清空购物车
 *
 */
@WebServlet(name = "CleanCartServlet", value = ["/CleanCartServlet"])
class CleanCartServlet:HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 清空 session 作用域中的购物车对象，然后刷新购物车页面
        req.session.removeAttribute("cart")
        resp.sendRedirect("cart.jsp")
    }

}