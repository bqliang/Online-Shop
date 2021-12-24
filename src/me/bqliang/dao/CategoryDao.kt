package me.bqliang.dao

import me.bqliang.model.Category
import me.bqliang.utils.myQR
import org.apache.commons.dbutils.handlers.BeanListHandler

/**
 * 分类数据库操作
 */
object CategoryDao {

    /**
     * 获取商品分类信息
     *
     */
    fun getCategories() = myQR.query("SELECT * FROM category", BeanListHandler(Category::class.java))

}