package me.bqliang

import me.bqliang.model.User
import me.bqliang.utils.myQR
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val user = User()
    val x = myQR.update(
        "INSERT INTO user VALUES (?,?,?,?,?,?,?,?,?,?,?)",
        "bqliang",
        "bqliang",
        "bqliang",
        "bqliang",
        "@bqliang",
        "182",
        "2001-01-03",
        "男",
        0,
        "code",
        "address"
    )
    println(x)
    val str: String? = null
    val y = str?:""
}