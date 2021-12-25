package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.ProductDao

/**
 * 搜索商品
 *
 */
@WebServlet(name = "SearchProductServlet", value = ["/SearchProductServlet"])
class SearchProductServlet: HttpServlet() {

    // 每页所显示商品的数量
    private val SIZE = 12

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 获取用户提交的信息
        val keyword = req.getParameter("search")
        val currentPage = req.getParameter("currentPage").toInt()

        // 调用 Dao 层从数据库中模糊搜索商品
       val searchResult = ProductDao.searchProduct(keyword)

        // 计算总页数
        val totalPage = if ((searchResult.size % SIZE) > 0) (searchResult.size / SIZE) +1
        else searchResult.size / SIZE

        // 根据当前页数去筛选搜索结果
        val products =
            searchResult.filterIndexed { index, _ ->
                (index >= (currentPage - 1) * SIZE ) && (index < currentPage * SIZE) && (index < searchResult.size)
            }

        // 设置数据并转发到 search.jsp
        req.apply {
            setAttribute("currentPage", currentPage)
            setAttribute("totalPage", totalPage)
            setAttribute("search", keyword)
            setAttribute("productList", products)
            getRequestDispatcher("search.jsp").forward(req, resp)
        }
    }
}