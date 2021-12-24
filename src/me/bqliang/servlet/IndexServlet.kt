package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.CategoryDao
import me.bqliang.model.Category
import me.bqliang.model.Product
import me.bqliang.service.ProductService

/**
 * 处理首页商品数据
 *
 */
@WebServlet(name = "IndexServlet", value = ["/IndexServlet"])
class IndexServlet : HttpServlet() {

    private lateinit var hotProducts : List<Product>
    private lateinit var newProducts : List<Product>
    private lateinit var categories : List<Category>

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse?) {

        // 获取热门和最新商品
        ProductService.getNewAndHotProducts()?.apply {
            hotProducts = get("hot_products")!!
            newProducts = get("new_products")!!
        }

        // 获取分类信息并设置到 session
        categories = CategoryDao.getCategories()

        // 设置数据到，然后将数据转发到首页
        req.apply {
            session.setAttribute("categoryList", categories)
            setAttribute("hotProductList", hotProducts)
            setAttribute("newProductList", newProducts)
            getRequestDispatcher("index.jsp").forward(req, resp)
        }
    }
}