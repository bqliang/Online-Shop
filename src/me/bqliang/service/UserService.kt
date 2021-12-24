package me.bqliang.service

import me.bqliang.dao.UserDao
import me.bqliang.model.User

object UserService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户（不存在该用户时返回 null）
     */
    fun login(username: String, password: String): User? {
        return try {
            UserDao.findUserByUsernameAndPassword(username, password)
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}