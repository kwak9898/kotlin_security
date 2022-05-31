package com.security.study.kotlin_security.account

import org.hibernate.annotations.CreationTimestamp
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Account(
    @Id @GeneratedValue
    var id: Long? = null,
    var email: String,
    var password: String,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: Set<AccountRole>,

    @CreationTimestamp
    var createDt: LocalDateTime = LocalDateTime.now()
) {
    fun getAuthorities(): User {
        return User(
            this.email, this.password,
            this.roles.stream().map { role -> SimpleGrantedAuthority("ROLE_$role") }.collect(Collectors.toSet())
        )
    }
}

enum class AccountRole {
    ADMIN, USER
}