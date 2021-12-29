package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.ProductDao

/**
 * 商品详情
 *
 */
@WebServlet(name = "ProductInfoServlet", value = ["/ProductInfoServlet"])
class ProductInfoServlet: HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 获取商品编号
        val pid = req.getParameter("pid")
        // 获取商品信息并设置到 request 作用域中，然后转发到商品详情页面
        ProductDao.findProductById(pid)?.let {
            req.setAttribute("product", it)
            req.getRequestDispatcher("product_info.jsp").forward(req, resp)
        }
    }
}