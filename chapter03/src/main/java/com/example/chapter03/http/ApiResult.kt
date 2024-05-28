package com.example.chapter03.http

/**
 * @author: LiFan
 * @date: 2022/4/13
 * @Description:
 */
public data class ApiResult<T>(
    var msg: String = "",
    var code: Int = -1,
    var data: T
) {

    fun checkCode(): Boolean {
        return code == 0
    }
}

