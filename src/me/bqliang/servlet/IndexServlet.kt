package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.CategoryDao
import me.bqliang.dao.ProductDao

/**
 * 处理首页商品数据
 *
 */
@WebServlet(name = "IndexServlet", value = ["/IndexServlet"])
class IndexServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse?) {
        // 从数据库中获取数据
        val hotProducts = ProductDao.getHotProducts()
        val newProducts = ProductDao.getNewProducts()
        val categories = CategoryDao.getCategories()

        /**
         * 设置数据，然后将数据转发到首页
         * 因为分类导航栏在别的页面存在，所以将分类信息设置到 session 作用域
         */
        req.apply {
            session.setAttribute("categoryList", categories)
            setAttribute("hotProductList", hotProducts)
            setAttribute("newProductList", newProducts)
            getRequestDispatcher("index.jsp").forward(req, resp)
        }
    }
}