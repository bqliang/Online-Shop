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
        val pid = req.getParameter("pid")
        val buyNum = req.getParameter("buyNum").toInt()
        val cart = req.session.getAttribute("cart") as Cart
        val cartItem = cart.cartItems
        cartItem[pid]?.buyNum = buyNum
        resp.sendRedirect("cart.jsp")
    }
}