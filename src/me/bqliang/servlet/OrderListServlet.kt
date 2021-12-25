package me.bqliang.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.bqliang.dao.OrderDao
import me.bqliang.dao.OrderItemDao
import me.bqliang.model.User

/**
 * 订单列表
 *
 */
@WebServlet(name = "OrderListServlet", value = ["/OrderListServlet"])
class OrderListServlet:HttpServlet() {

    // 每页所显示的订单数
    private val SIZE = 5

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        // 首先判断用户是否已登录
        val user = req.session.getAttribute("user") as User?
        if (user == null){
            resp.sendRedirect("login.jsp")
            return
        }

        val currentPage = req.getParameter("currentPage").toInt()
        val allOrderList = OrderDao.getOrderByUid(user.uid)
        allOrderList.forEach { it.orderItems = OrderItemDao.getOrderItemByOid(it.oid) }

        val totalPage = if (allOrderList.size % SIZE > 0) (allOrderList.size / SIZE) + 1 else allOrderList.size / SIZE

        // 根据当前的页码去筛选订单列表
        val orderList = allOrderList.filterIndexed { index, _ ->
            (index >= (currentPage - 1) * SIZE) && (index < currentPage * SIZE) && (index < allOrderList.size)
        }

        req.apply {
            setAttribute("currentPage", currentPage)
            setAttribute("totalPage", totalPage)
            setAttribute("orderList", orderList)
            getRequestDispatcher("order_list.jsp").forward(req, resp)
        }
    }
}