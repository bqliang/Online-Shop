package me.bqliang.service

import me.bqliang.dao.ProductDao
import me.bqliang.model.CartItem
import me.bqliang.model.Product

object ProductService {

    /**
     * 获取最热和最新商品
     *
     * @return 最热和最新商品（存储在 Map 中，可通过键名“hot_products”或“new_products”获取）
     */
    fun getNewAndHotProducts(): HashMap<String, List<Product>>? {
        return try {
            HashMap<String, List<Product>>().apply {
                put("hot_products", ProductDao.getHotProducts())
                put("new_products", ProductDao.getNewProducts())
            }
        }catch (e : Exception){
            e.printStackTrace()
            null
        }
    }


    /**
     * 获取指定商品编号的商品的所有信息
     *
     * @param pid 商品编号
     * @return 商品
     */
    fun getProductById(pid: String) : Product?{
        return try {
            ProductDao.findProductById(pid)
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }


    /**
     * 构建购物车项目
     *
     * @param pid 商品编号
     * @param num 商品数量
     * @return 新的购物车项目
     */
    fun createCartItem(pid: String, num: Int) = CartItem().apply {
            product = getProductById(pid)
            buyNum = num
        }
}