package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.model.Cart

/**
 * 修改购物车中商品的数量
 *
 */
@WebServlet(name = "UpdateBuyNumServlet", value = ["/UpdateBuyNumServlet"])
class UpdateBuyNumServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 获取用户提交的信息
        val pid = req.getParameter("pid")
        val buyNum = req.getParameter("buyNum").toInt()
        // 获取购物车对象
        val cart = req.session.getAttribute("cart") as Cart
        // 修改购物车 item
        val cartItem = cart.cartItems
        cartItem[pid]?.buyNum = buyNum
        // 重定向到购物车页面
        resp.sendRedirect("cart.jsp")
    }
}