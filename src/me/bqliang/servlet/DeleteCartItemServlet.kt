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
        val pid = req.getParameter("pid")
        val cart = req.session.getAttribute("cart") as Cart
        val cartItems = cart.cartItems
        cartItems.remove(pid)
        // 删除商品后如果购物车为空，则删除购物车
        if (cartItems.isEmpty()) req.session.removeAttribute("cart")
        resp.sendRedirect("cart.jsp")
    }
}