package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.UserDao
import me.bqliang.model.User
import java.text.SimpleDateFormat
import java.util.*

/**
 * 处理用户注册请求
 *
 */
@WebServlet(name = "RegisterServlet", value = ["/RegisterServlet"])
class RegisterServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 设置编码格式
        req.characterEncoding = "UTF-8"
        // 先判断账号是否已经存在
        val un= req.getParameter("username")
        val user = UserDao.findUserByUsername(un)
        if (user != null){
            // 账号已存在，设置错误信息转发到注册页面
            req.setAttribute("msg", "<script>alert('账号已经存在已经存在，重新注册...')</script>")
            req.getRequestDispatcher("register.jsp").forward(req, resp)
        }else{
            // 账号不存在，构建新用户保存到数据库
            val affectedRow = User().apply {
                uid = UUID.randomUUID().toString()
                username = req.getParameter("username")
                password = req.getParameter("password")
                name = req.getParameter("name")
                email = req.getParameter("email")
                telephone = req.getParameter("telephone")
                sex = req.getParameter("sex")
                birthday = req.getParameter("birthday")
                address = req.getParameter("address")
            }.let(UserDao::addUser)

            if (affectedRow == 1){
                // 数据库操作成功，跳转到登录页面
                resp.sendRedirect("login.jsp")
            }else{
                // 数据库操作失败，跳转到注册页面
                req.setAttribute("msg", "<script>alter('注册失败请重新填写注册信息...')</script>")
                req.getRequestDispatcher("register.jsp").forward(req, resp)
            }
        }
    }
}