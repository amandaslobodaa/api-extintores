package extintores_api.demo.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.JwtException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import extintores_api.demo.service.EmpresaService
@Component
class JWTUtil(
    private val empresaService: EmpresaService
) {
    private val expiration: Long = 1200000 // 20 minutos
    @field:Value("\${jwt.secret}")
    private lateinit var secret: String
    private fun getSigningKey() =
        Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(
        descricao: String,
        authorities: Collection<GrantedAuthority>
    ): String {
        return Jwts.builder()
            .subject(descricao)
            .claim(
                "role",
                authorities.map { it.authority }
            )
            .expiration(
                Date(System.currentTimeMillis() + expiration)
            )
            .signWith(getSigningKey())
            .compact()
    }
    fun isValid(jwt: String?): Boolean {
        if (jwt.isNullOrBlank()) {
            return false
        }
        return try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwt)
            true
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }
    fun getAuthentication(jwt: String?): Authentication {
        val claims = Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(jwt)
            .payload
        val username = claims.subject
        val rolesList = claims["role"] as? List<*>
            ?: emptyList<Any>()
        val authorities = rolesList.map {
            SimpleGrantedAuthority(it.toString())
        }
        return UsernamePasswordAuthenticationToken(
            username,
            null,
            authorities
        )
    }
}