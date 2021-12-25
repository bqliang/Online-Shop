package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.UserDao
import me.bqliang.model.User

/**
 * 更新用户资料
 *
 */
@WebServlet(name = "UpdateUserInfoServlet",  value = ["/UpdateUserInfoServlet"])
class UpdateUserInfoServlet : HttpServlet() {

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val user = req.session.getAttribute("user") as User

        // 调用 UserDao 更新数据库
        user.apply {
            password = req.getParameter("password")
            name = req.getParameter("name")
            email = req.getParameter("email")
            telephone = req.getParameter("telephone")
            sex = req.getParameter("sex")
            birthday = req.getParameter("birthday")
            address = req.getParameter("address")
        }.let(UserDao::updateUserInfo)
        println("fuck")

        // 重新设置user并重定向到用户资料页面
        req.session.setAttribute("user", user)
        resp.sendRedirect("user_info.jsp")
    }
}