package me.bqliang.dao

import me.bqliang.model.Product
import me.bqliang.utils.myQR
import org.apache.commons.dbutils.handlers.BeanHandler
import org.apache.commons.dbutils.handlers.BeanListHandler
import org.apache.commons.dbutils.handlers.ScalarHandler

/**
 * 商品数据库操作
 */
object ProductDao {

    /**
     * 获取最新商品列表
     *
     * @return 最新商品列表
     */
    fun getNewProducts(): List<Product> = myQR.query(
        "select * from product order by pdate desc limit 16",
        BeanListHandler(Product::class.java)
    )

    /**
     * 获取热门商品列表
     *
     * @return 热门商品列表
     */
    fun getHotProducts(): List<Product> = myQR.query(
        "select * from product where is_hot = 1",
        BeanListHandler(Product::class.java)
    )

    /**
     * 通过商品编号查询商品信息
     *
     * @param pid 商品编号
     * @return 商品
     */
    fun findProductById(pid: String): Product? = myQR.query(
        "select * from product where pid = ?",
        BeanHandler(Product::class.java),
        pid
    )


    /**
     * 查询对应分类中的商品数量
     *
     * @param cid
     */
    fun getCount(cid: String?): Int {
        var sql = ""
        var count = 0

        if (cid == null){
            // 没有给定分类编号，就查询所有商品数量
            sql = "SELECT count(pid) FROM product"
            count = myQR.query(sql, ScalarHandler())
        }else{
            sql = "SELECT count(pid) FROM product WHERE cid = ?"
            count = myQR.query(sql, ScalarHandler(), cid)
        }
        return count
    }


    /**
     * 分页查询商品记录
     *
     * @param startLine 从第几个商品开始显示
     * @param size 商品数量
     * @return 商品列表
     */
    fun getProduct(startLine: Int, size: Int): List<Product>? {
        val start = if(startLine <= 0) 0 else startLine
        val sql = "SELECT * FROM product LIMIT ?,?"
        return myQR.query(sql, BeanListHandler(Product::class.java), start, size)
    }


    /**
     * 通过商品分类 ID 来查找商品
     *
     * @param startLine 从第几个商品开始显示
     * @param size 商品数量
     * @param cid 商品分类
     * @return 商品列表
     */
    fun getProductByCid(startLine: Int, size: Int, cid: String): List<Product>? {
        val start = if (startLine <= 0) 0 else startLine
        val sql = "SELECT * FROM product WHERE cid = ? LIMIT ?,?"
        return myQR.query(sql, BeanListHandler(Product::class.java), cid, start, size)
    }


    /**
     * 通过关键字模糊搜索商品
     *
     * @param keyword 关键字
     * @return 商品列表
     */
    fun searchProduct(keyword : String) = myQR.query(
        "SELECT * FROM product WHERE pname like ? OR pdesc like ?",
        BeanListHandler(Product::class.java),
        "%${keyword}%",
        "%${keyword}%"
    )

}