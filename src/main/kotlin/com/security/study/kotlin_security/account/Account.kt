package com.security.study.kotlin_security.account

import java.time.LocalDateTime

data class Account(
    var id: Long,
    var email: String,
    var password: String,
    var roles: Set<AccountRole>,
    var createDt: LocalDateTime
)