package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.model.Cart
import me.bqliang.service.CartService

/**
 * 负责处理添加商品到购物车
 *
 */
@WebServlet(name = "AddCartServlet", value = ["/AddCartServlet"])
class AddCartServlet: HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 获取要添加的商品编号和数量
        val pid = req.getParameter("pid")
        val num = req.getParameter("buyNum").toInt()

        val session = req.session
        // 构建购物车 item
        val cartItem = CartService.createCartItem(pid, num)
        // 从会话中获取购物车
        var cart: Cart? = session.getAttribute("cart") as Cart?
        // 如果购物车为空，则构建新的购物车对象
        if (cart == null){
            cart = Cart()
        }

        // 判断购物车中是否已经存在相容商品，如果是则调整数量
        cart.cartItems.forEach {
            if (it.key == pid) cartItem.buyNum += it.value.buyNum
        }

        // 向购物车中添加 Cart Item
        cart.cartItems[pid] = cartItem
        // 将购物车设置到会话当中
        session.setAttribute("cart", cart)
        // 重定向到购物车页面
        resp.sendRedirect("cart.jsp")
    }
}