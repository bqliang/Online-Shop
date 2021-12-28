package me.bqliang.dao

import me.bqliang.model.User
import me.bqliang.utils.myQR
import org.apache.commons.dbutils.handlers.BeanHandler

/**
 * 用户数据库操作
 */
object UserDao {

    /**
     * 根据用户名与密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户（用户不存在时返回 null）
     */
    fun findUserByUsernameAndPassword(username: String, password: String): User? =
        myQR.query(
            "select * from user where username = ? and password = ?",
            BeanHandler(User::class.java),
            username,
            password
        )

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 影响行数
     */
    fun addUser(user: User) = myQR.update(
        "INSERT INTO user VALUES (?,?,?,?,?,?,?,?,?,?,?)",
        user.uid,
        user.username,
        user.password,
        user.name,
        user.email,
        user.telephone,
        user.birthday,
        user.sex,
        user.state,
        user.code,
        user.address
    )


    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    fun findUserByUsername(username: String): User? = myQR.query(
        "SELECT * FROM user where username = ?",
        BeanHandler(User::class.java),
        username
    )


    /**
     * 更新用户信息
     *
     * @param user 用户
     * @return 影响行数
     */
    fun updateUserInfo(user: User) = myQR.update(
        "UPDATE user " +
                "SET password = ?, name = ?, email = ?, telephone = ?, sex = ?, birthday = ?, address = ? " +
                "WHERE username = ?",
        user.password,
        user.name,
        user.email,
        user.telephone,
        user.sex,
        user.birthday,
        user.address,
        user.username
    )

}

