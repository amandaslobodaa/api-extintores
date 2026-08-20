package extintores_api.demo.security

import extintores_api.demo.config.JWTUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JWTAuthenticationFilter(
    private val jwtUtil: JWTUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        println("========== JWT FILTER ==========")
        println("URL: ${request.method} ${request.requestURI}")

        val authorization = request.getHeader("Authorization")

        println("AUTHORIZATION: $authorization")

        val token = getTokenDetail(authorization)

        println("TOKEN EXISTE: ${token != null}")

        if (token != null) {

            println("TOKEN VÁLIDO: ${jwtUtil.isValid(token)}")

            if (jwtUtil.isValid(token)) {

                val authentication = jwtUtil.getAuthentication(token)

                println("USUÁRIO: ${authentication.name}")
                println("AUTHORITIES: ${authentication.authorities}")

                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        println(
            "AUTHENTICATION FINAL: " +
                    SecurityContextHolder.getContext().authentication
        )

        println("===============================")

        filterChain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?): String? {

        if (token != null && token.startsWith("Bearer ", ignoreCase = true)) {
            return token.substring(7).trim()
        }

        return null
    }
}