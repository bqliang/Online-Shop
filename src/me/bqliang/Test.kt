package me.bqliang

import me.bqliang.dao.UserDao
import me.bqliang.model.User
import me.bqliang.utils.myQR
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val user = User()
    User().apply {
        username = "bqliang"
        address = "地球村"
    }.let(UserDao::updateUserInfo)

}