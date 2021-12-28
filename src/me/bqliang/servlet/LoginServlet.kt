package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.UserDao

/**
 * 处理用户登录
 *
 */
@WebServlet(name = "LoginServlet", value = ["/LoginServlet"])
class LoginServlet: HttpServlet() {

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        // 获取用户提交的用户名和密码
        val username: String = req.getParameter("username")
        val password: String = req.getParameter("password")
        // 在数据库中查找用户
        val user = UserDao.findUserByUsernameAndPassword(username, password)

        // 判断用户是否为空
        if (user != null) {
            // 用户不为空，则将数据设置到会话中，并重定向到首页
            req.session.setAttribute("user", user)
            resp.sendRedirect("IndexServlet")
        } else {
            // 用户为空，设置错误信息并转发到登录页面
            req.setAttribute("error", "账号或密码错误，请重试！")
            req.getRequestDispatcher("login.jsp").forward(req, resp)
        }
    }
}