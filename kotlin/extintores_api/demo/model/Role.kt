package extintores_api.demo.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "roles")
data class Role (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    val id_role: Long,
    @Column(name = "nome")
    val nome: String) : GrantedAuthority {

    override fun getAuthority() = nome
}