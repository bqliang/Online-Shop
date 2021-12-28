package me.bqliang.utils

import com.mchange.v2.c3p0.ComboPooledDataSource

val myDataSource: ComboPooledDataSource by lazy {
    ComboPooledDataSource().apply {
        driverClass = "com.mysql.cj.jdbc.Driver"
        jdbcUrl = "jdbc:mysql://localhost:3306/shopdb?characterEncoding=utf-8"
        user = "root"
        password = "mysql"
        // 初始化连接池的连接对象个数
        initialPoolSize = 10
    }
}