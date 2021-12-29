package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.model.Cart

/**
 * 处理从购物车中移除商品
 *
 */
@WebServlet(name = "DeleteCartItemServlet", value = ["/DeleteCartItemServlet"])
class DeleteCartItemServlet:HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 获取用户要移除的商品的编号
        val pid = req.getParameter("pid")
        // 获取购物车信息并从中移除商品
        val cart = req.session.getAttribute("cart") as Cart
        val cartItems = cart.cartItems
        cartItems.remove(pid)
        // 删除商品后如果购物车为空，则删除购物车对象
        if (cartItems.isEmpty()) req.session.removeAttribute("cart")
        // 重定向回购物车页面
        resp.sendRedirect("cart.jsp")
    }
}