package com.example.chapter03.http

/**
 * @author: LiFan
 * @date: 2022/4/21
 * @Description:
 */

data class UserInfo (
    var mac: String = "",
    var pos1: String = "",
    var pos2: String = "",
    var pos3: String = "",
    var position: String = "",
    var orgName: String = "",

    @Deprecated("")
    var lxfs: String = "",
    @Deprecated("")
    var lxrXm: String = "",

)
