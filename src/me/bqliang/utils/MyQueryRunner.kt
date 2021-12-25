package me.bqliang.utils

import org.apache.commons.dbutils.QueryRunner

val myQR by lazy { QueryRunner(myDataSource) }