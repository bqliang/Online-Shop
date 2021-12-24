package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.ProductDao


@WebServlet(name = "ProductListServlet", value = ["/ProductListServlet"])
class ProductListServlet: HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 获取分类 ID
        val cid = req.getParameter("cid")
        val size = 12 // 每页显示12个商品
        // 求出这个分类中的商品数量
        val count = ProductDao.getCount(cid)
        // 计算总页数
        val totalPage = if ((count % size) > 0) (count / size + 1) else (count / size)
        // 处理页码信息
        val s = req.getParameter("currentPage")?.toInt() // 获取页码信息

        // 如果当前页码为 NULL 则设置为 1
        var currentPage = req.getParameter("currentPage")?.toInt()?:1
        // 防止页码出错
        currentPage = if (currentPage <= 0) 1 else currentPage
        currentPage = if (currentPage >= totalPage) totalPage else currentPage


        val list = if (cid == null) {
            // 商品分类 ID 为空， 直接查询所有商品
            //（（当前页面-1）*每页显示数目 ---> 开始显示的记录行数）
            ProductDao.getProduct((currentPage - 1) * size, size)
        } else {
            // 商品分类 ID 不为空
            req.setAttribute("cid", cid)
            ProductDao.getProductByCid((currentPage - 1) * size, size, cid)
        }

        // 设置数据并转发
        req.apply {
            setAttribute("currentPage", currentPage)
            setAttribute("totalPage", totalPage)
            setAttribute("productList", list)
            getRequestDispatcher("product_list.jsp").forward(req, resp)
        }
    }
}