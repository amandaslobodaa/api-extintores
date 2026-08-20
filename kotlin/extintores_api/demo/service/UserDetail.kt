package extintores_api.demo.service

import extintores_api.demo.model.Empresa
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
class UserDetail(
    val empresa: Empresa
) : UserDetails {

    override fun getAuthorities() = empresa.role

    override fun getPassword() = empresa.senha

    override fun getUsername() = empresa.descricao

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}