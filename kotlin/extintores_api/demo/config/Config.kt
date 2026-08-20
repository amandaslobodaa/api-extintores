package extintores_api.demo.config

import extintores_api.demo.model.Credentials
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.core.userdetails.UserDetails

@RestController
@RequestMapping("/autorizacao")
class Config (private val authenticationManager: AuthenticationManager, private val jwtUtil: JWTUtil){

    @PostMapping("/login")
    fun autenticacao(@RequestBody login: Credentials): ResponseEntity<String> {
        val token = UsernamePasswordAuthenticationToken(login.descricao, login.senha)
        val authentication = authenticationManager.authenticate(token)
        val userDetails = authentication.principal as UserDetails
        val jwt = jwtUtil.generateToken(userDetails.username, userDetails.authorities)
        return ResponseEntity.ok(jwt)
    }

}