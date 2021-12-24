package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.model.User

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


    }
}